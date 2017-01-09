package com.graypn.cmmon.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Zip 处理类
 * <p>
 * Created by ZhuLei on 2016/11/28.
 * Email: zhuleineuq@gmail.com
 */

public final class ZipUtils {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

    private ZipUtils() {
    }

    /**
     * 对字节数组进行 GZIP 压缩
     */
    public static byte[] gZip(byte[] bContent) {
        if (bContent == null) {
            return null;
        }
        ByteArrayOutputStream bOut = null;
        GZIPOutputStream gOut = null;
        try {
            int ctLen = bContent.length;
            bOut = new ByteArrayOutputStream(ctLen);
            gOut = new GZIPOutputStream(bOut);
            gOut.write(bContent, 0, ctLen);
            gOut.finish();
            return bOut.toByteArray();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (gOut != null) {
                try {
                    gOut.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (bOut != null) {
                try {
                    bOut.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 对字节数组进行 GZIP 解压缩
     */
    public static byte[] unGZip(byte[] bContent) {
        if (bContent == null) {
            return null;
        } else {
            GZIPInputStream pIn = null;
            ByteArrayOutputStream bos = null;
            try {
                pIn = new GZIPInputStream(new ByteArrayInputStream(bContent));
                bos = new ByteArrayOutputStream();
                byte[] data = new byte[8192];
                int num;
                while ((num = pIn.read(data)) != -1) {
                    bos.write(data, 0, num);
                }
                bos.flush();
                return bos.toByteArray();
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                if (pIn != null) {
                    try {
                        pIn.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
}
