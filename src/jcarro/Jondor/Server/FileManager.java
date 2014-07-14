package jcarro.Jondor.Server;

import java.io.*;
import java.util.StringTokenizer;

import com.jcraft.jsch.*;

public class FileManager {
	Session session;
	ServerController serverController;

	public FileManager(Session session) {
		this.session = session;
		serverController = new ServerController(session);
	}

	public boolean upload(String source, String remotePath) {
		if (source.getBytes()[0] == '~')
			source = System.getProperty("user.home") + source.substring(1);
		if (remotePath.length() == 0)
			remotePath = ".";
		if (remotePath.getBytes()[0] == '~') {
			System.out.println("~ path is not permitted");
			return false;
		}

		ChannelSftp channelSftp = null;
		try {
			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();

			File f = new File(source);
			channelSftp.put(new FileInputStream(f),
					remotePath + "/" + f.getName());
			channelSftp.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			channelSftp.disconnect();
			return false;
		}
		return true;
	}

	public boolean download(String source, String localPath) {
		if (localPath.length() == 0)
			localPath = ".";
		if (localPath.getBytes()[0] == '~')
			localPath = System.getProperty("user.home")
					+ localPath.substring(1);
		if (source.getBytes()[0] == '~') {
			System.out.println("~ path is not permitted");
			return false;
		}

		ChannelSftp channelSftp = null;
		try {
			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();

			String fileName = null;
			StringTokenizer tokenizer = new StringTokenizer(source, "/");
			while (tokenizer.hasMoreTokens())
				fileName = tokenizer.nextToken();

			File f = new File(localPath + "/" + fileName);
			channelSftp.get(source, new FileOutputStream(f));
			channelSftp.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			channelSftp.disconnect();
			return false;
		}
		return true;
	}

	public boolean remove(String source) {
		String out = serverController.execCommand("rm " + source);

		if (out.compareTo("") == 0)
			return true;
		return false;
	}

}
