package util;

import entity.creature.Creature;
import entity.creature.animal.herbivore.Horse;
import entity.creature.animal.predator.Wolf;
import entity.creature.plant.Plant;

public class Settings {

  public final static int columnsCount = 10;
  public final static int rowsCount = 10;
  public final static int maxCountWolfOnLocation = 30;
  public final static int maxCountPlantOnLocation = 200;
  public final static int maxCountHorseOnLocation = 20;


  public int getProbabilityEat(Creature eater, Creature eatable) {
    if (eater instanceof Wolf) {
      if (eatable instanceof Wolf) {
        return 0;
      }
      if (eatable instanceof Horse) {
        return 10;
      }
    }
    if (eater instanceof Horse) {
      if (eatable instanceof Horse) {
        return 0;
      }
      if (eatable instanceof Plant) {
        return 100;
      }
    }
    return 0;
  }
}

