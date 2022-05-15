/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

/**
 *
 * @author diego
 * @comment Alisson
 */
public enum CharacterState {
    Idle(),
    //state without movement
    Walking(),
    //state for movement over a platform
    Jumping(),
    //state no colision with platforms, applicable for when moving on X axis or not.
    JumpingShooting(),
    //state with no colision with platforms, applicable when moving on X axis or not but the character is also in shooting state.
    Staggered(),
    //state for event of damage without death, indicates the player have suffered damage event, cancel other events such as shooting or Jumping.
    Dead(),
    //state for health = 0; used to trigger the death thread Event.
    Shooting(),
    //State used for animation and trigger the instanciation of projectiles, when not moving on X axis and in colision with a platform vertically.
    RunFiring();
    // State used for animation and trigger the instantiation of projectiles when moving on X axis during colision with a platform vertically.
}
//this enum interface will be used to alther character states used to change sprites and trigger events such as death.
