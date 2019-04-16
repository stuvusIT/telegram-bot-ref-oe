package first;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultLocation;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bottle extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		return "TIMKKBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasInlineQuery()) {
			update.getInlineQuery();
			AnswerInlineQuery answer = new AnswerInlineQuery();
			answer.setInlineQueryId(update.getInlineQuery().getId());
			InlineQueryResultLocation location = new InlineQueryResultLocation();
			location.setId(update.getInlineQuery().getId());
			location.setLatitude(48.773398f);
			location.setLongitude(9.164825f);
			location.setTitle("Standort Critical Mass");
			answer.setResults(location);
			try {
				execute(answer);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			if (update.getMessage().getFrom().getLanguageCode().equals("en")) {
				SendMessage message = new SendMessage(update.getMessage().getChatId(), "Long live the queen!");
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
			SendLocation method = null;
			String text = update.getMessage().getText();
			switch (text) {
			case "/woistnili":
				SendLocation location = new SendLocation().setChatId(update.getMessage().getChatId());
				location.setLatitude(48.745744f);
				location.setLongitude(9.103930f);
				method = location;
				break;
			case "/woisthds":
				location = new SendLocation().setChatId(update.getMessage().getChatId());
				location.setLatitude(48.746561f);
				location.setLongitude(9.107789f);
				method = location;
				break;
			case "/woistzfb":
				location = new SendLocation().setChatId(update.getMessage().getChatId());
				location.setLatitude(48.782426f);
				location.setLongitude(9.174578f);
				method = location;
				break;
			}
			try {
				execute(method);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotToken() {
		return "705844836:AAHzjdpsilRS8VTO9GIpMutEL4pR8s5D8t8";
	}
}