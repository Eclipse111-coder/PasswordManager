package com.example.passwordman.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static java.lang.constant.ConstantDescs.NULL;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.OK;

public class PasswordManager extends Application {
    public File passwordStorage = new File("Passwords storage");
    password passHelp = new password();
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        stage.setTitle("Password manager");

        TextField result = new TextField();
        result.setPromptText("Here will be result");
        result.setEditable(false);

        Button btnGenerateNewPassword = new Button("Generate");
        Button btnCopyPassword = new Button("Copy");

        Text passwordDiff = new Text("your password difficulty is " + passHelp.checkStrengthOfPassword());

        result.textProperty().addListener((observable, oldValue, newValue) -> {
            passHelp.setPassword(newValue);
            byte strength = passHelp.checkStrengthOfPassword();
            passwordDiff.setText("your password difficulty is " + strength);
        });

        btnGenerateNewPassword.setOnAction(e -> {
            Dialog<password> GeneratePassword = new Dialog<>();
            VBox generatePasswordVBox = new VBox(200);
            TextField passwordEncrypt = new TextField();
            passwordEncrypt.setPromptText("Type here your password");
            Text helpText = new Text("Skip if you didn`t wanna encrypt any word");
            TextField lengthOfPassword = new TextField();
            lengthOfPassword.setText("8");
            lengthOfPassword.setPromptText("type Here length of your password");
            Text helpTextWithLength = new Text("type here length");

            generatePasswordVBox.getChildren().addAll(passwordEncrypt, helpText,helpTextWithLength, lengthOfPassword);
            GeneratePassword.getDialogPane().setContent(generatePasswordVBox);
            GeneratePassword.getDialogPane().getButtonTypes().addAll(OK, CANCEL);

            GeneratePassword.setResultConverter(button -> {
                if (button == OK) {
                    String newPassword = "";
                        int tempIntForLoop = Integer.parseInt(lengthOfPassword.getText());
                        for (int i = 0; i < tempIntForLoop; i++) {
                            newPassword += passHelp.poolIndexChoose();
                        }
                        try (PrintWriter pw = new PrintWriter(new FileWriter(passwordStorage, true))) {
                            pw.println(newPassword);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        result.setText(findLastString());
                        passHelp.setPassword(result.getText());
                    }
                return null;
            });
            GeneratePassword.showAndWait();
        });

        btnCopyPassword.setOnAction(e -> {
            Clipboard copyPassword = Clipboard.getSystemClipboard();
            ClipboardContent passwordThatNeedToBeCopy = new ClipboardContent();
            passwordThatNeedToBeCopy.putString(findLastString());
            copyPassword.setContent(passwordThatNeedToBeCopy);
        });

        grid.add(btnGenerateNewPassword, 0, 1);
        grid.add(btnCopyPassword, 0, 2);
        grid.add(passwordDiff, 1, 0);
        grid.add(result, 0, 0);

        Scene scene = new Scene(grid, 320, 240);
        stage.setScene(scene);
        stage.show();
    }
    private String findLastString() {
        Scanner fileScan = null;
        String lastLine = "";
        try {
            fileScan = new Scanner(passwordStorage);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        while (fileScan.hasNextLine()) {
            lastLine = fileScan.nextLine();
        }
        return lastLine;
    }
}
