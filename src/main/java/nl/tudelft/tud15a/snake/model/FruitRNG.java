package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.command.EffectController;
import nl.tudelft.tud15a.snake.model.command.commands.MultiplyCommand;
import nl.tudelft.tud15a.snake.model.command.commands.WarpCommand;
import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.decorator.Golden;
import nl.tudelft.tud15a.snake.model.decorator.Metal;
import nl.tudelft.tud15a.snake.model.decorator.Silver;
import nl.tudelft.tud15a.snake.model.decorator.Warp;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;

public class FruitRNG implements CollisionListener {
    private Model model;
    

    public FruitRNG(Model model) {
        this.model = model;
    }

    @Override
    public void onCollision(CollisionReason reason) {
    	model.getEffectControler().executeAllCommand();
        model.getEffectControler().clear();
        Fruit fruit = new Apple();
        double random = Math.random();
        double random2 = Math.random();
        if(random <0.4 ){
        	fruit = new Warp(fruit);
        	model.getEffectControler().addCommand(new WarpCommand(model.getSnake()));
        }
        if(random2 < 0.2){
        	model.getEffectControler().addCommand(new MultiplyCommand(model.getSnake(), new Silver(new Apple())));
        	fruit = new Silver(fruit); 
        }
        else if(random2 >= 0.2 && random2 < 0.3){
        	model.getEffectControler().addCommand(new MultiplyCommand(model.getSnake(), new Golden(new Apple())));
        	fruit = new Golden(fruit);
        }
        
       
        
        model.setFruit(fruit);
        fruit.locate();
    }
}
