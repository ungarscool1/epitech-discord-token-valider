package wtf.ungarscool1.epitechintratoken;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageListener implements MessageCreateListener{

	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessage().isPrivateMessage() && event.getMessageAuthor().isBotOwner() && event.getMessageAttachments().get(0).isImage()) {
			IntraParser parser = new IntraParser(Main.config.epiToken);
			BufferedImage image = event.getMessageAttachments().get(0).downloadAsImage().join();
			File output = new File("./token.png");

			try {
				ImageIO.write(image, "png", output);
			} catch (IOException e) {
				e.printStackTrace();
			}
			event.getChannel().sendMessage(parser.validateToken("./token.png"));
		} else {
			if (event.getMessageAuthor().isBotUser())
				return;
			if (event.getMessageAttachments().get(0).isImage()) {
				event.getChannel().sendMessage("Vous ne pouvez pas valider votre token Epitech pour les raisons suivante:\n- Vous essayez de valider un token depuis un serveur.\n- Vous n'êtes pas le propriétaire du bot.");
			}
		}
	}

}
