package com.example.demo6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

            // Array to hold Image objects
        Image[] images = new Image[9];
        ButtonBar Bar = new ButtonBar();


        // Load images from files (replace paths with your actual image locations)
        String src = Objects.requireNonNull(getClass().getResource("image1.jpg")).toExternalForm();
        String src1 = Objects.requireNonNull(getClass().getResource("image3.jpg")).toExternalForm();
        String src2 = Objects.requireNonNull(getClass().getResource("image2.jpg")).toExternalForm();
        String src3 = Objects.requireNonNull(getClass().getResource("image9.jpg")).toExternalForm();
        String src4 = Objects.requireNonNull(getClass().getResource("image7.jpg")).toExternalForm();
        String src5= Objects.requireNonNull(getClass().getResource("image5.jpg")).toExternalForm();
        String src6= Objects.requireNonNull(getClass().getResource("image4.jpg")).toExternalForm();
        String src7= Objects.requireNonNull(getClass().getResource("image6.jpg")).toExternalForm();
        String src8= Objects.requireNonNull(getClass().getResource("image8.jpg")).toExternalForm();

        images[0] = new Image(src);
        images[1] = new Image(src1);
        images[2] = new Image(src2);
        images[3] = new Image(src3);
        images[4] = new Image(src4);
        images[5] = new Image(src5);
        images[6] = new Image(src6);
        images[7] = new Image(src7);
        images[8] = new Image(src8);

            // Create a GridPane to display the images
        GridPane imageGrid = new GridPane();
        imageGrid.setHgap(10);//spacing between images
        imageGrid.setVgap(10);

        // Create navigation buttons
        final int[] currentIndex = {0};//index of currently displayed image

        ImageView display = new ImageView();
        display.setImage(images[currentIndex[0]]);
        display.setFitWidth(400); // Adjust image size as needed
        display.setPreserveRatio(true);

        Button previous = new Button("PREVIOUS");
        previous.setId("button");
        previous.setOnAction(event -> {
            currentIndex[0] = (currentIndex[0] - 1 + images.length) % images.length;
            display.setImage(images[currentIndex[0]]);
        });

        Button nextButton = new Button("Next");
        nextButton.setId("button");
        nextButton.setOnAction(event -> {
            currentIndex[0] = (currentIndex[0] + 1) % images.length;
            display.setImage(images[currentIndex[0]]);
        });
        Button clear = new Button("RESET");
        clear.setId("button");
        clear.setOnAction(event ->{
            currentIndex[0] = 0;
            display.setImage(null);

        });

        //stage
        Pane root = new Pane();
        //box to contain grid and images
        Pane box = new Pane();
        box.setId("box");

        box.setLayoutX(30);
        box.setLayoutY(30);

        Pane v = new Pane();
        v.setLayoutX(700);
        v.setLayoutY(60);

            // Create ImageViews, add event handlers, and place them in the grid
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(images[i]);
            imageView.setFitWidth(200); // Adjust image size as needed
            imageView.setPreserveRatio(true);

            final int imageIndex = i; // Capture the image index for the event handler

            imageView.setOnMouseClicked(event -> {
                ImageView Clicked = new ImageView();

                Image ClickedImage = (images[imageIndex/3 * 3 + imageIndex%3]);

                display.setImage(ClickedImage);
                display.setFitWidth(400);
                display.setPreserveRatio(true);
            });
            imageGrid.add(imageView, i % 3, i/3); // Arrange images in a grid
        }

        // attach the css file
        var cssFile = getClass().getResource("Style.css");
        Bar.getButtons().addAll(previous,nextButton,clear);
        Bar.setId("buttons");
        Bar.setLayoutX(700);
        Bar.setLayoutY(10);

        box.getChildren().addAll(imageGrid);
        v.getChildren().add(display);
        root.getChildren().addAll(box,v,Bar);

        // Set the scene and show the stage
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssFile.toExternalForm());// attach the css file
        primaryStage.setScene(scene);
        primaryStage.setTitle("IMAGE GALLERY");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}