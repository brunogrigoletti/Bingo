package app;
import ui.Frame;

public class App{
    Frame frame;

    public App(){
        frame = new Frame();
    }

    public void execute(){
        frame.newFrame();
    }
}