public class OrbitMovement extends MovementBehavior {
    

    public OrbitMovement() {
        this.MovementType = "Orbit";
    }

    /* (non-Javadoc)
     * @see Entity#move()
     * 
     * Replace abstract Entity movement with Orbiter movement
     * Moves (clockwise or counterclockwise)
     */
    @Override
    public void move(Entity entity, Dungeon dungeon) {
        Room current_room = entity.getLocation();
        String direction = entity.getDirection();

        Integer level = current_room.getLevel();
        Integer row = current_room.getRow();
        Integer column = current_room.getColumn();

        if (direction.equals("clockwise")) {
            // Orbit Clockwise
            if (row == 0 && column == 0) {
                String next_room_name = new String("(" + level + "-0-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 0 && column == 1) {
                String next_room_name = new String("(" + level + "-0-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 0 && column == 2) {
                String next_room_name = new String("(" + level + "-1-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 1 && column == 2) {
                String next_room_name = new String("(" + level + "-2-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 2) {
                String next_room_name = new String("(" + level + "-2-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 1) {
                String next_room_name = new String("(" + level + "-2-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 1 && column == 0) {
                String next_room_name = new String("(" + level + "-0-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            }
        } else {
            // Orbit Counterclockwise;
            if (row == 0 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 1 && column == 0) {
                String next_room_name = new String("(" + level + "-2-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-2-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 1) {
                String next_room_name = new String("(" + level + "-2-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 2 && column == 2) {
                String next_room_name = new String("(" + level + "-1-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 1 && column == 2) {
                String next_room_name = new String("(" + level + "-0-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 0 && column == 2) {
                String next_room_name = new String("(" + level + "-0-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            } else if (row == 0 && column == 1) {
                String next_room_name = new String("(" + level + "-0-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                entity.setLocation(new_room);
            }
        }
    }
}
