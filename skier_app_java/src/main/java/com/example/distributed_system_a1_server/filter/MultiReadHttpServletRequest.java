package com.example.distributed_system_a1_server.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private final byte[] cachedBody;

    public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        InputStream requestInputStream = request.getInputStream();
        this.cachedBody = IOUtils.toByteArray(requestInputStream);
    }

    @Override
    public BufferedReader getReader() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    @Override
    public ServletInputStream getInputStream() {
        class CachedBodyServletInputStream extends ServletInputStream {

            private final InputStream cachedBodyInputStream;

            public CachedBodyServletInputStream(byte[] cachedBody) {
                this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
            }

            @Override
            public boolean isFinished() {
                try {
                    return cachedBodyInputStream.available() == 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int read() throws IOException {
                return cachedBodyInputStream.read();
            }
        }

        return new CachedBodyServletInputStream(this.cachedBody);
    }
}
