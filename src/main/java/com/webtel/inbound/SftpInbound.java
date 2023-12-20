package com.webtel.inbound;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.sshd.sftp.client.SftpClient;
import org.junit.jupiter.api.Test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

public class SftpInbound
{
    @Test
    public void runDemo()
    {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/SftpInboundReceiveSample.xml", this.getClass());
        RemoteFileTemplate<SftpClient.DirEntry> template = null;
        //String file1 = "INV_33401.xml";
        //new File("local-dir", file1).delete();
        try
        {
            PollableChannel localFileChannel = context.getBean("receiveChannel", PollableChannel.class);
            @SuppressWarnings("unchecked")
            SessionFactory<SftpClient.DirEntry> sessionFactory = context.getBean(CachingSessionFactory.class);
            template = new RemoteFileTemplate<>(sessionFactory);
           // SftpTestUtils.createTestFiles(template, file1);

            SourcePollingChannelAdapter adapter = context.getBean(SourcePollingChannelAdapter.class);
            adapter.start();

            Message<?> received = localFileChannel.receive();
            assertNotNull("Expected file", received);
            System.out.println("Received first file message: " + received);
        }

        finally
        {
            context.close();
        }
    }
}
