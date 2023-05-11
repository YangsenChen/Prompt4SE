private static void adjustPen()
  {
    //     Change the color of the line the tortoise draws to the next color on the color wheel --#6
    Tortoise.setPenColor(ColorWheel.getNextColor());
    //     Increase the tortoises pen width by 1 --#15                                               
    Tortoise.setPenWidth(Tortoise.getPenWidth() + 1.0);
    //     If the tortoise's pen width is greater than 4, then --#17
    if (Tortoise.getPenWidth() > 4)
    {
      //     Reset the pen width to 1 --#16
      Tortoise.setPenWidth(1);
    }
  }