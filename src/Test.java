import java.util.Vector;

import com.jcraft.jsch.*;

import java.io.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String sftpHost="172.17.37.11";
		String sftpUser="GSDevSFTP";
		String sftpPassword="IronIMS123";
		int sftpPort=22;
		
		String filePath="/Jegan";
		
		JSch jsch = new JSch();
		Session session;
		try {
				session = jsch.getSession(sftpUser, sftpHost, sftpPort);
				session.setPassword(sftpPassword);
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				config = null;
				session.connect();
				
				Channel channel = session.openChannel("sftp");
				channel.connect();
				
				
				ChannelSftp channelSftp = (ChannelSftp) channel;
				channelSftp.cd(filePath);
				
				
				Vector<ChannelSftp.LsEntry> filelist = channelSftp.ls(filePath);
				
				for (ChannelSftp.LsEntry lsEntry : filelist) {
					System.out.println("File name="+lsEntry.getFilename());
					System.out.println("Time ="+lsEntry.getLongname());
					
					try {
						InputStream in=channelSftp.get(lsEntry.getFilename());
						byte[] bArray = new byte[in.read()];
						
						System.out.println("in=="+in);
						System.out.println("AVailable=="+bArray);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
		} catch (JSchException | SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
