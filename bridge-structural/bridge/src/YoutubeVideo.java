public class YoutubeVideo extends Video {

    public YoutubeVideo(VideoProcessor processor) {
        super(processor);
    }

    @Override
    public void play(String fileName) {
        processor.process(fileName);
        //now play
    }
}
