import forms.UMLEditorForm;
import javax.swing.*;

import constants.UMLConstants;
import core.CanvasManager;
import core.DrawerManager;
import core.ModeManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                initializeApplication();
            } catch (Exception e) {
                handleInitializationError(e);
            }
        });
    }

    private static void initializeApplication() throws Exception {
        LOGGER.info("Application starting...");
        setupShutdownHook();
        setupLookAndFeel();
        setupManagers();
        launchUI();
        LOGGER.info("Application started successfully");
    }

    private static void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Application shutting down...");
        }));
    }

    private static void setupLookAndFeel() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    private static void setupManagers() {
        ModeManager modeManager = ModeManager.getInstance();
        modeManager.addModeChangeListener(CanvasManager.getInstance());
        modeManager.addModeChangeListener(DrawerManager.getInstance());
    }

    private static void launchUI() {
        UMLEditorForm form = new UMLEditorForm();
        form.show();
    }

    private static void handleInitializationError(Exception e) {
        String errorMessage = String.format(UMLConstants.ERROR_MESSAGE_FORMAT, e.getMessage());
        LOGGER.log(Level.SEVERE, errorMessage, e);

        JOptionPane.showMessageDialog(
                null,
                errorMessage,
                UMLConstants.ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}