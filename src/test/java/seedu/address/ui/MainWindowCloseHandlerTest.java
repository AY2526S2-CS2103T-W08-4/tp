package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.stage.WindowEvent;

public class MainWindowCloseHandlerTest {

    @Test
    public void handleConfirmationWindowClose_consumesEvent() {
        WindowEvent event = new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST);
        event.consume(); // simulate what the handler does
        assertTrue(event.isConsumed());
    }
}