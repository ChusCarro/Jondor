package jcarro.Jondor.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.*;

public class ServerController {

	protected Session session;

	public ServerController(Session session) {
		this.session = session;
	}

	public String execCommand(String command) {
		String output = null;

		ChannelExec channel = null;
		try {
			if (!session.isConnected())
				session.connect();
			channel = (ChannelExec) session.openChannel("exec");

			channel.setCommand(command);

			channel.connect();
			InputStream in = channel.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(in));

			String line = input.readLine();

			output = "";

			while (line != null) {
				output += line + "\n";
				line = input.readLine();
			}

		} catch (JSchException e) {
			e.printStackTrace();
			channel.disconnect();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			channel.disconnect();
			return null;
		}

		channel.disconnect();
		return output;
	}

}
