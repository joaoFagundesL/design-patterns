package button;
public class ButtonBuilder {
  private int positionX;
  private int positionY;
  private int width;
  private int height;
  private String letter;

  public ButtonBuilder withLetter(final String letter) {
    this.letter = letter;
    return this;
  }

  public ButtonBuilder withPosition(final int positionX, final int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
    return this;
  }

  public ButtonBuilder withSize(final int width, final int height) {
    this.width = width;
    this.height = height;
    return this;
    }

  public Button build() {
    return new Button(letter, positionX, positionY, width, height);
  }
}
