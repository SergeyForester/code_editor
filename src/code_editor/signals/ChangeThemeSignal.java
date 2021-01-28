package signals;

import javafx.event.Event;
import javafx.event.EventType;

public class ChangeThemeSignal extends Event {
	public static final EventType<ChangeThemeSignal> EVENT_TYPE =
			new EventType<>(Event.ANY, "ChangeThemeSignal");


	public ChangeThemeSignal() {
		super(EVENT_TYPE);
	}

}
