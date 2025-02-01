package util;

import entity.creature.Creature;
import entity.creature.animal.herbivore.*;
import entity.creature.animal.predator.*;
import entity.creature.plant.Plant;

public class Settings {

  public final static int columnsCount = 10;
  public final static int rowsCount = 10;

  public final static int maxCountWolfOnLocation = 30;
  public final static int maxCountFoxOnLocation = 30;
  public final static int maxCountBoaOnLocation = 30;
  public final static int maxCountBearOnLocation = 5;
  public final static int maxCountEagleOnLocation = 20;

  public final static int maxCountPlantOnLocation = 200;

  public final static int maxCountHorseOnLocation = 20;
  public final static int maxCountDeerOnLocation = 20;
  public final static int maxCountBuffaloOnLocation = 10;
  public final static int maxCountCaterpillarOnLocation = 1000;
  public final static int maxCountGoatOnLocation = 140;
  public final static int maxCountSheepOnLocation = 140;
  public final static int maxCountRabbitOnLocation = 150;
  public final static int maxCountMouseOnLocation = 500;
  public final static int maxCountBoarOnLocation = 50;
  public final static int maxCountDuckOnLocation = 200;


  public int getProbabilityEat(Creature eater, Creature eatable) {
    if (eater instanceof Wolf) {
      if (eatable instanceof Horse) {
        return 10;
      }
      if (eatable instanceof Deer) {
        return 15;
      }
      if (eatable instanceof Rabbit) {
        return 60;
      }
      if (eatable instanceof Mouse) {
        return 80;
      }
      if (eatable instanceof Goat) {
        return 60;
      }
      if (eatable instanceof Sheep) {
        return 70;
      }
      if (eatable instanceof Boar) {
        return 15;
      }
      if (eatable instanceof Buffalo) {
        return 10;
      }
      if (eatable instanceof Duck) {
        return 40;
      }
    }

    if (eater instanceof Boa) {
      if (eatable instanceof Fox) {
        return 15;
      }
      if (eatable instanceof Rabbit) {
        return 20;
      }
      if (eatable instanceof Mouse) {
        return 40;
      }
      if (eatable instanceof Duck) {
        return 10;
      }
    }

    if (eater instanceof Fox) {
      if (eatable instanceof Rabbit) {
        return 70;
      }
      if (eatable instanceof Mouse) {
        return 90;
      }
      if (eatable instanceof Duck) {
        return 60;
      }
      if (eatable instanceof Caterpillar) {
        return 40;
      }
    }

    if (eater instanceof Bear) {
      if (eatable instanceof Boa) {
        return 80;
      }
      if (eatable instanceof Horse) {
        return 40;
      }
      if (eatable instanceof Deer) {
        return 80;
      }
      if (eatable instanceof Rabbit) {
        return 80;
      }
      if (eatable instanceof Mouse) {
        return 90;
      }
      if (eatable instanceof Goat) {
        return 70;
      }
      if (eatable instanceof Sheep) {
        return 70;
      }
      if (eatable instanceof Boar) {
        return 50;
      }
      if (eatable instanceof Buffalo) {
        return 20;
      }
      if (eatable instanceof Duck) {
        return 10;
      }
    }

    if (eater instanceof Eagle) {
      if (eatable instanceof Fox) {
        return 10;
      }
      if (eatable instanceof Rabbit) {
        return 90;
      }
      if (eatable instanceof Mouse) {
        return 90;
      }
      if (eatable instanceof Duck) {
        return 80;
      }
    }

    if (eater instanceof Horse || eater instanceof Deer || eater instanceof Rabbit || eater instanceof Sheep
    || eater instanceof Goat || eater instanceof Buffalo || eater instanceof Caterpillar) {
      if (eatable instanceof Plant) {
        return 100;
      }
    }

    if(eater instanceof Mouse || eater instanceof Boar || eater instanceof Duck ){
      if (eatable instanceof Plant) {
        return 100;
      }
      if (eatable instanceof Caterpillar) {
        return 90;
      }
    }
    return 0;
  }
}

