public class ButtonBuilder {
  private int x;
  private int y;
  private int width;
  private int height;
  private String letter;

  public ButtonBuilder withLetter(String letter) {
    this.letter = letter;
    return this;
  }

  public ButtonBuilder withPosition(int x, int y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public ButtonBuilder withSize(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  public Button build() {
    return new Button(letter, x, y, width, height);
  }
}
