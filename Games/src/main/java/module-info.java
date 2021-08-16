/*
 * module-info.java 1.0 15/04/2020
 */

// Author: Matthew Williams (maw101)

module uk.ac.aber.cs221.group14.dictionary {
   requires com.google.gson;
   requires javafx.controls;
   requires javafx.fxml;

   // allow access to javafx at runtime
   opens minigames.frontend to javafx.fxml;

   // allow frontend access
   exports minigames.frontend;
}