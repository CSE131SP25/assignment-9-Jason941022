package assignment9;

import java.util.LinkedList;

public class Snake {

        private static final double SEGMENT_SIZE = 0.05;
        private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 0.5;
        private LinkedList<BodySegment> segments;
        private double deltaX;
        private double deltaY;

        public Snake() {
                deltaX = 0;
                deltaY = 0;
                segments = new LinkedList<BodySegment>();
                segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        }

        public void changeDirection(int direction) {
                if(direction == 1) { //up
                        deltaY = MOVEMENT_SIZE;
                        deltaX = 0;
                } else if (direction == 2) { //down
                        deltaY = -MOVEMENT_SIZE;
                        deltaX = 0;
                } else if (direction == 3) { //left
                        deltaY = 0;
                        deltaX = -MOVEMENT_SIZE;
                } else if (direction == 4) { //right
                        deltaY = 0;
                        deltaX = MOVEMENT_SIZE;
                }
        }

        /**
         * The snake attempts to eat the given food, growing if it does so successfully
         * @param f the food to be eaten
         * @return true if the snake successfully ate the food
         */
        public boolean eat(Food food) {
                BodySegment head = segments.get(0);
                double dis = Math.sqrt((head.getX()-food.getX())*
                                (head.getX()-food.getX())+
                                (head.getY()-food.getY())*
                                (head.getY()-food.getY()));
                if(dis<(head.getSize()+Food.getFoodSize())) {
                        BodySegment tail = segments.get(segments.size()-1);
                        segments.add(new BodySegment(tail.getX()+SEGMENT_SIZE, tail.getY()+SEGMENT_SIZE,SEGMENT_SIZE));
                        return true;
                }
                return false;
        }


        /**
         * Moves the snake by updating the position of each of the segments
         * based on the current direction of travel
         */
        public void move() {
                BodySegment tail = segments.get(segments.size()-1);
                BodySegment head = segments.get(0);
                tail.setX(deltaX + head.getX());
                tail.setY(deltaY + head.getY());
                segments.addFirst(segments.get(segments.size()-1));
                segments.remove(segments.size()-1);
        }

        

        /**
         * Draws the snake by drawing each segment
         */
        public void draw() {
                for(BodySegment e : segments) {
                        e.draw();
                }
        }
        

        /**
         * Returns true if the head of the snake is in bounds
         * @return whether or not the head is in the bounds of the window
         */
        public boolean isInbounds() {
                BodySegment head = segments.get(0);
                if(head.getX()>1-SEGMENT_SIZE || head.getX()<SEGMENT_SIZE ||
                                head.getY()>1-SEGMENT_SIZE || head.getY()<SEGMENT_SIZE) {
                        return false;
                }
                return true;
        }

}

