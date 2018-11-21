package com.chhd.y;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class LogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        if (false) {
            filter.doFilter(request, response);
            return;
        }
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        if (!(request instanceof HttpServletRequest)) {
            return;
        }
        int i = 0;
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        sb.append("\n######################## 请求开始 ########################");
        sb.append("\n请求路径: " + url);
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) { // 循环遍历Header中的参数，把遍历出来的参数放入Map中
            if (i == 0) {
                sb.append("\n请求头部: \n");
                i += 1;
            }
            String key = headerNames.nextElement().toString();
            String value = req.getHeader(key);
            sb.append(key + ": " + value + " & ");
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        if (entries.size() != 0) {
            sb.append("\n拼接参数: \n");
        }
        for (Map.Entry<String, String[]> map : entries) {
            for (String value : map.getValue()) {
                sb.append(map.getKey() + ": " + value + " & ");
            }
        }
        i = 0;
        RequestWrapper requestWrapper = new RequestWrapper(req);
        BufferedReader bufferedReader = requestWrapper.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (i == 0) {
                sb.append("\n请求参数: \n");
                i += 1;
            }
            sb.append(line);
        }
        if (!(response instanceof HttpServletResponse)) {
            return;
        }
        HttpServletResponse resp = (HttpServletResponse) response;
        ResponseWrapper responseWrapper = new ResponseWrapper(resp);
        filter.doFilter(requestWrapper, responseWrapper);
        String result = new String(responseWrapper.getResponseData(), "utf-8");
        response.setContentLength(-1); // 解决可能在运行的过程中页面只输出一部分
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
        sb.append("\n请求结果:\n" + result);
        long end = System.currentTimeMillis();
        sb.append("\n######################## 请求结束 ########################(" + (end - start) + "ms)\n");
        logger.info(sb.toString());
    }

    @Override
    public void destroy() {

    }

    private class RequestWrapper extends HttpServletRequestWrapper {

        private byte[] body;

        RequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = input2byte(request.getInputStream());
        }

        private byte[] input2byte(InputStream inStream) throws IOException {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc;
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                stream.write(buff, 0, rc);
            }
            return stream.toByteArray();
        }


        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            final ByteArrayInputStream stream = new ByteArrayInputStream(body);
            return new ServletInputStream() {

                public boolean isFinished() {
                    return false;
                }

                public boolean isReady() {
                    return false;
                }

                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() throws IOException {
                    return stream.read();
                }
            };
        }
    }

    public class ResponseWrapper extends HttpServletResponseWrapper {

        private ByteArrayOutputStream buffer = null;//输出到byte array
        private ServletOutputStream out = null;
        private PrintWriter writer = null;

        ResponseWrapper(HttpServletResponse resp) throws IOException {
            super(resp);
            buffer = new ByteArrayOutputStream();// 真正存储数据的流
            out = new DefaultOutputStream(buffer);
            writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return out;
        }

        @Override
        public PrintWriter getWriter() throws UnsupportedEncodingException {
            return writer;
        }

        @Override
        public void flushBuffer() throws IOException {
            if (out != null) {
                out.flush();
            }
            if (writer != null) {
                writer.flush();
            }
        }

        @Override
        public void reset() {
            buffer.reset();
        }

        byte[] getResponseData() throws IOException {
            flushBuffer();
            return buffer.toByteArray();
        }

        private class DefaultOutputStream extends ServletOutputStream {
            private ByteArrayOutputStream bos = null;

            DefaultOutputStream(ByteArrayOutputStream stream) throws IOException {
                bos = stream;
            }

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                bos.write(b, 0, b.length);
            }

            public boolean isReady() {
                return false;
            }

            public void setWriteListener(WriteListener writeListener) {

            }
        }
    }
}
