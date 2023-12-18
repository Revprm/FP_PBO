package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helper.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import static Addition.Constants.Towers.*;
import static Addition.Constants.Projectiles.*;

public class ProjectileManager {

    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;
    public ProjectileManager(Playing playing){
        this.playing = playing;
        importImgs();
    }

    private void importImgs(){
        // 16 17 18
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[3];
        for(int i = 0; i < 3; i++){
            proj_imgs[i] = atlas.getSubimage((i + 16) * 32, 0, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e){
        int type = getProjectileType(t);
        
        int xDist = (int)Math.abs(t.getX() - e.getX());
        int yDist = (int)Math.abs(t.getY() - e.getY());
        int totalDist = xDist + yDist;

        float xPerc = (float)xDist / totalDist;
        float yPerc = 1.0f - xPerc;

        float xSpeed = xPerc * Addition.Constants.Projectiles.GetSpeed(type);
        float ySpeed = Addition.Constants.Projectiles.GetSpeed(t.getTowerType()) - xSpeed;

        if(t.getX() > e.getX()){
            xSpeed *= -1;
        }
        else if(t.getY() > e.getY()){
            ySpeed *= -1;
        }
        projectiles.add(new Projectile(t.getX()+16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), proj_id++, type));
    }

    private int getProjectileType(Tower t) {
        switch(t.getTowerType()){
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }
        return 0;
    }

    public void update(){
        for(Projectile p : projectiles){
            if(p.isActive()){
                p.move();
                if(isProjectileHit(p)){
                    p.setActive(false);
                }
                else{
                    // nothing
                }
            }
        }
    }
    private boolean isProjectileHit(Projectile p) {
        for(Enemy e : playing.getEnemyManager().getEnemies()){
            if(e.getBounds().contains(p.getPos())){
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g){
        for(Projectile p : projectiles){
            if (p.isActive()) {
                g.drawImage(proj_imgs[p.getProjectileType()], (int)p.getPos().x, (int)p.getPos().y, null);
            }
        }
    }
}
