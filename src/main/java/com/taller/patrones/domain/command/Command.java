package com.taller.patrones.domain.command;

public interface Command {
    void execute();
    void undo();
}
