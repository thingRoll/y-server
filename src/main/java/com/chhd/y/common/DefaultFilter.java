package com.chhd.y.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(DefaultFilter.class);

    ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        sb.append("\nurl: " + url);
        sb.append("\nheader: \n");
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {//循环遍历Header中的参数，把遍历出来的参数放入Map中
            String key = headerNames.nextElement().toString();
            String value = req.getHeader(key);
            sb.append(key + ": " + value + " & ");
        }
        sb.append("\nparameterMap: \n");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> map : entries) {
            for (String value : map.getValue()) {
                sb.append(map.getKey() + ": " + value + " & ");
            }
        }
        sb.append("\nparam: \n");
        RequestWrapper requestWrapper = new RequestWrapper(req);
        BufferedReader bufferedReader = requestWrapper.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        filter.doFilter(request, response);

        if (!(response instanceof HttpServletResponse)) {
            return;
        }
        HttpServletResponse resp = (HttpServletResponse) response;
        ResponseWrapper responseWrapper = new ResponseWrapper(resp);
        String result = new String(responseWrapper.getResponseData());
        sb.append("\nresult:\n" + result);

        logger.info(sb.toString());
    }

    @Override
    public void destroy() {

    }

    public class RequestWrapper extends HttpServletRequestWrapper {

        private byte[] body;

        public RequestWrapper(HttpServletRequest request) throws IOException {
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

        private ByteArrayOutputStream buffer = null;
        private ServletOutputStream out = null;
        private PrintWriter writer = null;

        public ResponseWrapper(HttpServletResponse response) throws IOException {
            super(response);

            buffer = new ByteArrayOutputStream();// 真正存储数据的流
            out = new DefaultOutputStream(buffer);
            writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
        }

        /**
         * 重载父类获取outputstream的方法
         */
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

        public byte[] getResponseData() throws IOException {
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
