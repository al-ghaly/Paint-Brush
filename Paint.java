/* It would have been a better design to seperate the Shapes (Parent and children)
   in a seperate folder (and files), any utility classes in a sepatate folder, and leave
   the main class to play it solo in this file, but the project is too simple to do so.
 */
import java.applet.Applet; // Import the package to handle the applet application
import java.awt.*;  // Import all the classes from the AWT Package
import java.awt.event.*; // Import all the classes from the event package
import java.util.ArrayList; // import the ArrayList class


/**
 * This is the main class (The brain) for our application
 * Here the magic is done
 */
public class Paint extends Applet{
    // Those are the main UI Buttons/elements
    Button rectButton, lineButton, ovalButton, penButton, eraserBotton;
    Button hLineButton, vLineButton, circleButton, squareButton;
    Button clearButton, redoButton, undoButton, redButton, greenButton, blueButton;
    Button blackButton, cyanButton, pinkButton, yelloButton;
    Checkbox solidCheckbox;
    Font font;
    Color color; // The color that the user has chosen

    // Those are the attributes that we will need for the backend.
    Shape shape; // The current shape that the user has chosen to draw
    boolean solid; // Wether the user wants to fill or draw a shape
    boolean valid; // Wether the user is drawing inside the painting area or not
    ArrayList<Shape> shapes; // All the shapes that the user has previously drawn
    ArrayList<Shape> trash; // The shapes that the user has chosen to get red of

    // This enum holds the different possible drawing modes for the user to chose from
    enum modes {RECTANGLE, OVAL, LINE, ERASER, PEN, CIRCLE, SQUARE, VER_LINE, HOR_LINE}
    // This variable holds the current mode that the user has chosen
    modes mode;

    /**
     * Here we initialize the application and set up the environment for the magic to start
     * @return nothing.
     */
    public void init(){
        // Initialize the array lists
        shapes = new ArrayList<Shape>();
        trash = new ArrayList<Shape>();
        // Initialize the start up mode as a line
        mode = modes.LINE;
        // Initialize the color
        color = Color.BLACK;
        // Initialize the solid property
        solid = false;
        // Add the buttons to the top of the application for the user to be able to customize the drawing
        drawButtons();
        // Add event listeners to the paint area
        paintListen();
        // Add event listeners to the base buttons
        buttonsListen();
    }

    /**
     * Here we do all the magic in just 2 lines of code!!
     * This is the paint method that paints the whole application.
     * @param g The graphics object to paint the application on.
     * @return nothing.
     */
    public void paint(Graphics g){
        /*
            As I have already stated before I strongly beleive in abstraction, so
            let's break down the complicated details into 2 lines of code.
         */
        // Draw the static members of the application
        drawLayout(g);
        // Draw the user drawn shapes
        drawShapes(g);
    }

    /**
     * Here we draw the static members of the application.
     * The alyout of the painter
     * @param g The graphics object to paint the application on.
     * @return nothing.
     */
    void drawLayout(Graphics g){
        // Draw the border
        g.fillRect(0, 0, getWidth(), getHeight() / 6); // The static members will take up 25% of the screen
        g.setColor(Color.WHITE);
        g.fillRect(5, 5, getWidth() - 6, getHeight() / 6 - 10);
        g.setColor(Color.BLACK);
    }

    /**
     * Here we draw the shapes that the user have chosen to draw.
     * @param g The graphics object to paint the application on.
     * @return nothing.
     */
    void drawShapes(Graphics g){
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    /**
     * Here we draw the buttons for the user to chose which shape he wants to create.
     * @return nothing.
     */
    void drawButtons(){
        // Draw the buttons for the custom shapes
        rectButton = new Button("Rectangle");
        lineButton = new Button("Line");
        ovalButton = new Button("Oval");
        penButton = new Button("Pen");
        eraserBotton = new Button("Erase");

        hLineButton = new Button("H Line");
        vLineButton = new Button("V Line");
        circleButton = new Button("Circle");
        squareButton = new Button("Square");
        // Add those buttons to the applet
        add(rectButton);
        add(squareButton);
        add(lineButton);
        add(vLineButton);
        add(hLineButton);
        add(ovalButton);
        add(circleButton);
        add(new Label());
        add(new Label());
        add(new Label());
        add(penButton);
        add(eraserBotton);
        // Just a separator
        add(new Label());
        add(new Label());
        add(new Label());

        // Draw the buttons for the custom functionalities
        clearButton = new Button("Clear");
        add(new Label());
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");

        redButton = new Button("Red");
        greenButton = new Button("Green");
        blueButton = new Button("Blue");
        blackButton = new Button("Black");
        yelloButton = new Button("Yellow");
        pinkButton = new Button("Pink");
        cyanButton = new Button("Cyan");
        // Add those buttons to the applet
        add(clearButton);
        add(new Label());
        add(undoButton);
        add(redoButton);
        // Just a separator
        add(new Label());
        add(new Label());
        add(new Label());
        // Color Buttons
        add(blackButton);
        add(redButton);
        add(greenButton);
        add(blueButton);
        add(yelloButton);
        add(pinkButton);
        add(cyanButton);

        solidCheckbox = new Checkbox("Solid");
        // Just a separator
        add(new Label());
        add(new Label());
        add(solidCheckbox);

        // style the buttons
        font = new Font("Arial", Font.PLAIN, 24);
        Component[] items =
                {rectButton, lineButton, ovalButton,
                        penButton, eraserBotton, vLineButton, hLineButton,
                        circleButton, squareButton,
                        clearButton, undoButton,
                        redoButton, redButton, greenButton, blueButton,
                        blackButton, yelloButton, pinkButton, cyanButton, solidCheckbox};
        // Set the font for each button in the array
        for (Component item : items) {
            item.setFont(font);
        }
    }

    /**
     * In this method we need to add event listeners to all the UI Buttons to statr
     * acting as a response to each user input
     * @return nothing.
     */
    void buttonsListen(){
        // Handle Rectangle button functionality
        rectButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the draw rectangle mode
                        mode = modes.RECTANGLE;
                    }
                }
        );
        // Handle Line button functionality
        lineButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the draw Line mode
                        mode = modes.LINE;
                    }
                }
        );
        // Handle Oval button functionality
        ovalButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the draw Oval mode
                        mode = modes.OVAL;
                    }
                }
        );

        // Handle Vertical Line button functionality
        vLineButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the draw a vertical line
                        mode = modes.VER_LINE;
                    }
                }
        );
        // Handle Horizontal Line button functionality
        hLineButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the draw a horizontal line
                        mode = modes.HOR_LINE;
                    }
                }
        );
        // Handle Circle button functionality
        circleButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the draw a circle
                        mode = modes.CIRCLE;
                    }
                }
        );
        // Handle Square button functionality
        squareButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen to draw a square
                        mode = modes.SQUARE;
                    }
                }
        );

        // Handle Pen button functionality
        penButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the free style drawing mode
                        mode = modes.PEN;
                    }
                }
        );
        // Handle Erase button functionality
        eraserBotton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the eraser
                        mode = modes.ERASER;
                    }
                }
        );
        // Handle Clear button functionality
        clearButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // Remove all the shapes and empty the trash
                        shapes.clear();
                        trash.clear();
                        repaint();
                    }
                }
        );
        // Handle Undo button functionality
        undoButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        if (shapes.size() > 0) // Undo is only available if we have shapes to remove
                        {
                            // remove the last shape from the shapes array and put it in the trash
                            trash.add(shapes.get(shapes.size() - 1));
                            shapes.remove(shapes.size() - 1);
                            repaint();
                        }
                    }
                }
        );
        // Handle Redo button functionality
        redoButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        if (trash.size() > 0)
                        {
                            // append the last element in the trash to the shapes array
                            shapes.add(trash.get(trash.size() - 1));
                            trash.remove(trash.size() - 1);
                            repaint();
                        }
                    }
                }
        );
        // Handle Red Color button functionality
        redButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the red color
                        color = Color.RED;
                    }
                }
        );
        // Handle Green Color button functionality
        greenButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the green color
                        color = Color.GREEN;
                    }
                }
        );
        // Handle Blue button functionality
        blueButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the blue color
                        color = Color.BLUE;
                    }
                }
        );
        // Handle Red Color button functionality
        yelloButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the yellow color
                        color = Color.YELLOW;
                    }
                }
        );
        // Handle Black Color button functionality
        blackButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the black color
                        color = Color.BLACK;
                    }
                }
        );
        // Handle Pink button functionality
        pinkButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the pink color
                        color = Color.PINK;
                    }
                }
        );
        // Handle Cyan button functionality
        cyanButton.addActionListener(
                new  ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        // The user has chosen the cyan color
                        color = Color.CYAN;
                    }
                }
        );
        // Handle Solid CheckBox functionality
        solidCheckbox.addItemListener(
                new  ItemListener(){
                    public void itemStateChanged(ItemEvent ev){
                        solid = solidCheckbox.getState();
                    }
                }
        );
    }

    /**
     * In this method we need to add event listeners to mouse events, to be able to
     * take input from the user
     * @return nothing.
     */
    void paintListen(){
        // Create mouse listeners to listen to user interactions inside the paint area
        addMouseListener(
                new  MouseAdapter(){
                    // Handle the mouse press event
                    public void mousePressed (MouseEvent e){
                        // We need only the painting area (Not the top UI) to be sensetive
                        if (e.getY() > getHeight() / 6)
                        {
                            // We create a shape object according to the current mode set by the user
                            switch (mode) {
                                case RECTANGLE:
                                    // Create a Rectangle object
                                    shape = new Rectangle();
                                    break;
                                case OVAL:
                                    // Create an Oval object
                                    shape = new Oval();
                                    break;
                                case LINE:
                                    // Create a Line object
                                    shape = new Line();
                                    break;
                                case CIRCLE:
                                    // Create a Circle object
                                    shape = new Circle();
                                    break;
                                case SQUARE:
                                    // Create a Square object
                                    shape = new Square();
                                    break;
                                case VER_LINE:
                                    // Create a Vertical Line object
                                    shape = new VerLine();
                                    break;
                                case HOR_LINE:
                                    // Create a Horizontal Line object
                                    shape = new HorLine();
                                    break;
                                case PEN:
                                    // Create a free style pen object
                                    shape = new Pen();
                                    break;
                                case ERASER:
                                    // Create an Eraser Object
                                    shape = new Eraser();
                                    break;
                            }
                            // Update the coordinates of the shape
                            shape.setX1(e.getX());
                            shape.setY1(e.getY());
                            // Update the color fot the shape object
                            shape.setColor(color);
                            // Update the solid state for the shape object
                            shape.setSolid(solid);
                            //Add the shape to the list of shapes
                            shapes.add(shape);
                            valid = true;
                        }
                        else {
                            valid = false;
                        }
                    }
                    // Handle the mouse release event
                    public void mouseReleased (MouseEvent e){
                        /* If the user just pressed without any drag, we don't need to store
                         The object (Empty shape) to the array */
                        if (e.getY() > getHeight() / 6 // If the drag happens inside the paint area
                                && shape != null       // and an empty object is drawn in the paint area
                                && (shape.getX2() == 0 | shape.getY2() == 0)) // And the object is just a point
                        {
                            // In case if the user is using the pen mode (As expected) => don't remove the element
                            //(obj1 instanceof MyClass))
                            if (!
                                    ((shape instanceof Pen)
                                            && (shape.size() > 1))
                            )
                                //remove this element from the array as it is an empty shape
                                shapes.remove(shapes.size() - 1);
                        }
                        else
                        {
                            trash.clear(); // The user has drawn a new shape, so he can't redo what he has undone
                        }
                        // Print the size off the array to ackniwledge the current state
                        System.out.println("The user has drawn: " + (shapes.size()) + " Shapes!");
                    }
                }
        );
        addMouseMotionListener(
                new  MouseAdapter(){
                    public void mouseDragged (MouseEvent e){
                        // We need only the painting area (Not the top UI) to be sensetive
                        if (e.getY() > getHeight() / 6 && valid)
                        {
                            // Update the end points for the shape
                            shape.setX2(e.getX());
                            shape.setY2(e.getY());
                            // Repaint
                            repaint();
                        }
                    }
                }
        );
    }
}

/**
 * This is an abstract class that represent a DRAWABLE shape, each shape is defined by
 * a point, 2 dimensions, solid (fill or draw) property, and a draw functionality.
 */
abstract class Shape{
    /*
        - I strongly beleive in encapsulation and abstraction, so:
            . All the attributes will be ONLY accessed
              through the designed setters and getters.
            .  Each method will be considered as a black box that takes an input and
               produces an output, all you need to know about each method will be
               written for you as labels on the black boxes (Method Docstring).
     */
    // Each shape should have a start point (x, y) and specified dimensions
    private int x1, y1, x2, y2;
    // Each shape could even be solid or shallow
    private boolean solid;
    private Color color;

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The Y Coordinate of the second point of the shape.
     */
    public int getY2() {
        return y2;
    }

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The X Coordinate of the second point of the shape.
     */
    public int getX2() {
        return x2;
    }

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The X Coordinate of the shape's start point.
     */
    public int getX1() {
        return x1;
    }

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The Y Coordinate of the shape's start point.
     */
    public int getY1() {
        return y1;
    }

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The color if the shape.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This is a simple get mothod that takes no parameters.
     * @return Wether the shape is solid or shallow.
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * This is a simple set mothod that updates the shape's solid feature.
     * @param solid The new value of solid.
     * @return nothing.
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    /**
     * This is a simple set mothod that updates the shape's height.
     * @param y2 The new value of the height.
     * @return nothing.
     */
    public void setY2(int y) {
        this.y2 = y;
    }

    /**
     * This is a simple set mothod that updates the shape's width.
     * @param x2 The new value of the width.
     * @return nothing.
     */
    public void setX2(int x) {
        this.x2 = x;
    }

    /**
     * This is a simple set mothod that updates the X Coordinate's value.
     * @param x The new value of X.
     * @return nothing.
     */
    public void setX1(int x) {
        this.x1 = x;
    }

    /**
     * This is a simple set mothod that updates the Y Coordinate's value.
     * @param y The new value of Y.
     * @return nothing.
     */
    public void setY1(int y) {
        this.y1 = y;
    }

    /**
     * This is a simple set mothod that updates the shape's color.
     * @param color The new color.
     * @return nothing.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public abstract void draw(Graphics g);

    /**
     * This method calculates the width of the shape using its start and end points
     * @return The width of the shape.
     */
    public int getWidth(){
        return x2 > x1 ? x2 - x1 : x1 - x2;
    }

    /**
     * This method calculates the height of the shape using its start and end points
     * @return The height of the shape.
     */
    public int getHeight(){
        return y2 > y1 ? y2 - y1 : y1 - y2;
    }

    /**
     * Some shapes may have a size, so we need to implement that as a shape's method
     * @return The shapes Size.
     */
    public int size(){
        return 0;
    }
}

/**
 * This class represents the Rectangle shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Rectangle extends Shape{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Rectangle.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        // Update the color of the brush
        g.setColor(getColor());
        if (isSolid()) // Draw or fill according to the user choice
            g.fillRect(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth(), getHeight());
        else
            g.drawRect(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth(), getHeight());
    }
}

/**
 * This class represents the Oval shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Oval extends Shape{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Oval.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        g.setColor(getColor());
        if (isSolid())
            g.fillOval(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth(), getHeight());
        else
            g.drawOval(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth(), getHeight());
    }
}

/**
 * This class represents the line shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Line extends Shape{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Line.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}

/**
 * This class represents the Square shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Square extends Rectangle{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Square.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        // Update the color of the brush
        g.setColor(getColor());
        if (isSolid()) // Draw or fill according to the user choice
            g.fillRect(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth() > getHeight() ? getWidth(): getHeight(),
                    getWidth() > getHeight() ? getWidth(): getHeight());
        else
            g.drawRect(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth() > getHeight() ? getWidth(): getHeight(),
                    getWidth() > getHeight() ? getWidth(): getHeight());
    }
}

/**
 * This class represents the Circle shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Circle extends Oval{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Circle.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        // Update the color of the brush
        g.setColor(getColor());
        if (isSolid()) // Draw or fill according to the user choice
            g.fillOval(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth() > getHeight() ? getWidth(): getHeight(),
                    getWidth() > getHeight() ? getWidth(): getHeight());
        else
            g.drawOval(getX1() < getX2() ? getX1() : getX2(), // Start from the upper left corner (Here LEFT)
                    getY1() < getY2() ? getY1() : getY2(),    // Start from the upper left corner (Here UPPER)
                    getWidth() > getHeight() ? getWidth(): getHeight(),
                    getWidth() > getHeight() ? getWidth(): getHeight());
    }
}

/**
 * This class represents the Vertical line shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class VerLine extends Line{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Vertical Line.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawLine(getX1(), getY1(), getX1(), getY2());
    }
}

/**
 * This class represents the Horizontal line shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class HorLine extends Line{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for Horizontal Line.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawLine(getX1(), getY1(), getX2(), getY1());
    }
}

/**
 * This class represents the Pen shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Pen extends Shape{
    ArrayList<Point> points;
    // tempo point to hold the current point (Active point on the free shape)
    Point point;

    /**
     * This is a simple constructor method to set up the points array.
     * @return nothing.
     */
    Pen(){
        this.points = new ArrayList<Point>();
    }

    /**
     * We need to override the default behaviour from the shape class, when the user tries to set
     * any coordiates values (x, y) for the pen we will add it to the points array, to hold all
     * the points that the user dragged the mouse on not only the start and end point.
     * @param The x coordinate of the start point for the pen
     * @return nothing.
     */
    @java.lang.Override
    public void setX1(int x) {
        point = new Point();
        point.setX(x);
    }

    @java.lang.Override
    public void setX2(int x) {
        point = new Point();
        point.setX(x);

    }

    @java.lang.Override
    public void setY1(int y) {
        point.setY(y);
        points.add(point);
    }

    @java.lang.Override
    public void setY2(int y) {
        point.setY(y);
        points.add(point);
    }

    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for the free style pen.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        // Update the shape's color
        g.setColor(getColor());
        // Loop over the point to start drawing the lines
        for (int i = 1; i < points.size(); i++){
            g.drawLine(points.get(i - 1).getX(), points.get(i - 1).getY(),
                    points.get(i).getX(), points.get(i).getY());
        }
    }

    @java.lang.Override
    public int size(){
        return this.points.size();
    }
}

/**
 * This class represents the Horizontal Eraser shape and as it is a shape it extends the Shape class
 * and implements its own drawing functionality.
 */
class Eraser extends Pen{
    /**
     * Each shape shoud has its own draw method, so we expect each shape to provide
     * its own drawing behavior => Here is the draw method for the Eraser.
     * @param g The graphics object to draw our shape on.
     * @return nothing.
     */
    public void draw(Graphics g){
        // Set the color to the background's color.
        g.setColor(Color.WHITE);
        // Loop over the point to start drawing the lines
        for (int i = 0; i < points.size(); i++){
            g.fillRect(points.get(i).getX(), points.get(i).getY(),
                    20, 20);
        }
    }
}

/**
 * This class represents a point that has 2 coordinates (values) x and y.
 */
class Point{
    // The point's coordinates
    int x, y;

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The Y Coordinate of the point.
     */
    public int getY() {
        return y;
    }

    /**
     * This is a simple get mothod that takes no parameters.
     * @return The X Coordinate of the point.
     */
    public int getX() {
        return x;
    }

    /**
     * This is a simple set mothod that updates the Y coordinate.
     * @param The new value for Y.
     * @return nothing.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This is a simple set mothod that updates the X coordinate.
     * @param The new value for X.
     * @return nothing.
     */
    public void setX(int x) {
        this.x = x;
    }
}

