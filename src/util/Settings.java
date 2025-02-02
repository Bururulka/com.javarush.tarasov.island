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

  private Settings(){

  }

  public static int getProbabilityEat(Class eater, Class eatable) {
    if (eater.equals(Wolf.class)) {
      if (eatable.equals(Horse.class)) {
        return 10;
      }
      if (eatable.equals(Deer.class)) {
        return 15;
      }
      if (eatable.equals(Rabbit.class)) {
        return 60;
      }
      if (eatable.equals(Mouse.class)) {
        return 80;
      }
      if (eatable.equals(Goat.class)) {
        return 60;
      }
      if (eatable.equals(Sheep.class)) {
        return 70;
      }
      if (eatable.equals(Boar.class)) {
        return 15;
      }
      if (eatable.equals(Buffalo.class)) {
        return 10;
      }
      if (eatable.equals(Duck.class)) {
        return 40;
      }
    }

    if (eater.equals(Boa.class)) {
      if (eatable.equals(Fox.class)) {
        return 15;
      }
      if (eatable.equals(Rabbit.class)) {
        return 20;
      }
      if (eatable.equals(Mouse.class)) {
        return 40;
      }
      if (eatable.equals(Duck.class)) {
        return 10;
      }
    }

    if (eater.equals(Fox.class)) {
      if (eatable.equals(Rabbit.class)) {
        return 70;
      }
      if (eatable.equals(Mouse.class)) {
        return 90;
      }
      if (eatable.equals(Duck.class)) {
        return 60;
      }
      if (eatable.equals(Caterpillar.class)) {
        return 40;
      }
    }

    if (eater.equals(Bear.class)) {
      if (eatable.equals(Boa.class)) {
        return 80;
      }
      if (eatable.equals(Horse.class)) {
        return 40;
      }
      if (eatable.equals(Deer.class)) {
        return 80;
      }
      if (eatable.equals(Rabbit.class)) {
        return 80;
      }
      if (eatable.equals(Mouse.class)) {
        return 90;
      }
      if (eatable.equals(Goat.class)) {
        return 70;
      }
      if (eatable.equals(Sheep.class)) {
        return 70;
      }
      if (eatable.equals(Boar.class)) {
        return 50;
      }
      if (eatable.equals(Buffalo.class)) {
        return 20;
      }
      if (eatable.equals(Duck.class)) {
        return 10;
      }
    }

    if (eater.equals(Eagle.class)) {
      if (eatable.equals(Fox.class)) {
        return 10;
      }
      if (eatable.equals(Rabbit.class)) {
        return 90;
      }
      if (eatable.equals(Mouse.class)) {
        return 90;
      }
      if (eatable.equals(Duck.class)) {
        return 80;
      }
    }

    if (eater.equals(Horse.class) || eater.equals(Deer.class) || eater.equals(Rabbit.class) ||
            eater.equals(Sheep.class) || eater.equals(Goat.class) || eater.equals(Buffalo.class) ||
                    eater.equals(Caterpillar.class)) {
      if (eatable.equals(Plant.class)) {
        return 100;
      }
    }

    if(eater.equals(Mouse.class) || eater.equals(Boar.class) || eater.equals(Duck.class) ){
      if (eatable.equals(Plant.class)) {
        return 100;
      }
      if (eatable.equals(Caterpillar.class)) {
        return 90;
      }
    }
    return 0;
  }
}

