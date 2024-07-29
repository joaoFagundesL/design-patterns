public abstract class Video {
    public VideoProcessor processor;

    public Video(VideoProcessor processor) {
        this.processor = processor;
    }

    public abstract void play(String fileName);
}
