//package com.webtel.inbound;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.containsString;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//
//import org.apache.sshd.sftp.client.SftpClient;
//
//import org.springframework.integration.file.remote.RemoteFileTemplate;
//import org.springframework.integration.file.remote.SessionCallback;
//public class SftpTestUtils
//{
////    public static void createTestFiles(RemoteFileTemplate<SftpClient.DirEntry> template, final String... fileNames) {
////        if (template != null)
////        {
////            final ByteArrayInputStream stream = new ByteArrayInputStream("foo".getBytes());
////            template.execute((SessionCallback<SftpClient.DirEntry, Void>) session ->
////            {
////                try
////                {
////                    session.mkdir("si.sftp.sample");
////                }
////                catch (Exception e)
////                {
////                    assertThat(e.getMessage(), containsString("failed to create"));
////                }
////                for (int i = 0; i < fileNames.length; i++)
////                {
////                    stream.reset();
////                    session.write(stream, "si.sftp.sample/" + fileNames[i]);
////                }
////                return null;
////            });
////        }
////    }
//
////    public static void cleanUp(RemoteFileTemplate<SftpClient.DirEntry> template, final String... fileNames)
////    {
////        if (template != null)
////        {
////            template.execute((SessionCallback<SftpClient.DirEntry, Void>) session ->
////            {
////                for (int i = 0; i < fileNames.length; i++)
////                {
////                    try
////                    {
////                        session.remove("si.sftp.sample/" + fileNames[i]);
////                    }
////                    catch (IOException e)
////                    {
////                    }
////                }
////
////                // should be empty
////                session.rmdir("si.sftp.sample");
////                return null;
////            });
////        }
////    }
//
//    public static boolean fileExists(RemoteFileTemplate<SftpClient.DirEntry> template, final String... fileNames)
//    {
//        if (template != null)
//        {
//            return template.execute(session ->
//            {
//                SftpClient channel = (SftpClient) session.getClientInstance();
//                for (String fileName : fileNames)
//                {
//                    try
//                    {
//                        var stat = channel.stat("si.sftp.sample/" + fileName);
//                        if (stat == null)
//                        {
//                            System.out.println("stat returned null for " + fileName);
//                            return false;
//                        }
//                    }
//                    catch (IOException e)
//                    {
//                     System.out.println("Remote file not present: " + e.getMessage() + ": " + fileName);
//                        return false;
//                    }
//                }
//                return true;
//            });
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//}
