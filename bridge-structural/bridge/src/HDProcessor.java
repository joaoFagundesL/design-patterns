public class HDProcessor implements VideoProcessor {
    @Override
    public void process(String videoFile) {
        System.out.println("HDProcessor: Processing video file: " + videoFile);
    }
}
