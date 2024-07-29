/*
* Imagine I have a Video class and subclasses: YoutubeVideo, NetflixVideo. However, I also got the type of the video (HD or 4K), and thus
I'll have to create YoutubeVideoHD, YoutubeVideo4K, NetlixVideoHD, NetflixVideo4K. Notice this is exponential. Imagine I introduce a
Amazon video, then it is necessary to create multiple subclasses.

With the bridge pattern, we can handle this by creating a new class, let's call VideoProcessor (our bridge). Now, let's create another
2 classes that will implement the methods of VideoProcessor: UHD4KProcessor and HDProcessor (the types). For the video, we have YoutubeVideo and
NetflixVideo inheriting from Video. Inside Video, we make a reference to VideoProcessor so that when I instantiate in the Main I specify the type, whether
it is 4k or HD.
* */

public class Main {
    public static void main(String[] args) {
        Video youtubeVideo = new YoutubeVideo(new HDProcessor());
        youtubeVideo.play("youtube.mp4");

        Video netflixVideo = new NetflixVideo(new UHD4KProcessor());
        netflixVideo.play("netflix.mp4");
    }
}