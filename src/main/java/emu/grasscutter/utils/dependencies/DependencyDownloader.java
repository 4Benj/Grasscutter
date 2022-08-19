package emu.grasscutter.utils.dependencies;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.utils.FileUtils;
import emu.grasscutter.utils.JsonUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public class DependencyDownloader {

    private static String BLOCK_UNICODE;
    private static String BLOCK_UNICODE_10;

    static {
        try {
            BLOCK_UNICODE = new String(("█").getBytes("UTF-8"), Charset.defaultCharset().name());
            BLOCK_UNICODE_10 = new String(("██████████").getBytes("UTF-8"), Charset.defaultCharset().name()); // TODO: FIX
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void DownloadAllDependencies(String destination) {
        DependencyData dependencyData = JsonUtils.decode(new String(FileUtils.readResource("/dependencies.json")), DependencyData.class);

        for(DependencyData.Dependency dependency : dependencyData.dependencies) {
            String dependencyURLString = dependency.repo +
                (dependency.group + "." + dependency.name).replace(".", "/") + "/" + dependency.version + "/" +
                (dependency.name + "-" + dependency.version) + ".jar";

            try {
                URL url = new URL(dependencyURLString);

                DownloadDependency(url, destination);
            } catch (MalformedURLException exception) {
                Grasscutter.getLogger().error("Unable to resolve dependency URL: " + dependencyURLString);
                exception.printStackTrace();
            }
        }
    }

    public static boolean DownloadDependency(URL url, String destination) {
        PrintWriter writer = new PrintWriter(System.out, true, Charset.forName("UTF-8"));

        String[] urlSplits = url.getPath().split("/");
        String fileName = urlSplits[urlSplits.length - 1];

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("HEAD");
            long finalFileSize = httpConnection.getContentLengthLong();

            BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(destination + fileName);

            byte dataBuffer[] = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                long currentFileSize = Files.size(Path.of(destination + fileName));

                // disguise the download printer lol
                writer.print(GetDownloadPercentageString(fileName, currentFileSize, finalFileSize) + "\r");
            }
            writer.println(fileName + " [" + BLOCK_UNICODE_10 + "] 100%");
        } catch (IOException e) {
            // TODO: handle exception
            Grasscutter.getLogger().error("Unable to download dependency.");
            Grasscutter.getLogger().error(e.getMessage());
            e.printStackTrace();
        }

        return true;
    }

    private static String GetDownloadPercentageString(String fileName, long currentBytes, long targetBytes) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        String downloadPercentage = decimalFormat.format(((float) currentBytes/targetBytes)*100) + "%";
        String currentMB = ReadableByteConverter.formatSize(currentBytes, ReadableByteConverter.MiB, "MB");
        String targetMB = ReadableByteConverter.formatSize(targetBytes, ReadableByteConverter.MiB, "MB");

        final int parts = 10;
        int completeParts = (int) Math.floor((((float) currentBytes/targetBytes)*100) / parts);
        int incompleteParts = 10 - completeParts;

        StringBuilder text = new StringBuilder(fileName + " [");
        for (int i = 1; i <= completeParts; i++) {
            text.append(BLOCK_UNICODE);
        }

        for (int i = 1; i <= incompleteParts; i++) {
            text.append(" ");
        }

        text.append("] " + downloadPercentage + " (" + currentMB + "/" + targetMB + ")");
        return text.toString();
    }

    public final class ReadableByteConverter {
        private static long BYTE = 1L;
        private static long KiB = BYTE << 10;
        private static long MiB = KiB << 10;
        private static long GiB = MiB << 10;
        private static long TiB = GiB << 10;
        private static long PiB = TiB << 10;
        private static long EiB = PiB << 10;

        private static DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");

        private static String formatSize(long size, long divider, String unitName) {
            return DEC_FORMAT.format((double) size / divider) + " " + unitName;
        }
    }
}
