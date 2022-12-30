package com.filesdependencies.Models.Interface;

public interface IInput {
    /**
     * @return the command provided by the user.
     */
    String getCommand();

    /**
     * @return the filepath provided by the user.
     */
    String getFilepath();

    /**
     * @return the text for the text file provided by the user.
     */
    String getText();
}
