package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
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

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        // 16 17 18
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            proj_imgs[i] = atlas.getSubimage((i + 16) * 32, 0, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int xDist = (int) (t.getX() - e.getX());
        int yDist = (int) (t.getY() - e.getY());
        int totalDist = Math.abs(xDist) + Math.abs(yDist);

        float xPerc = (float) Math.abs(xDist) / totalDist;

        float xSpeed = xPerc * Addition.Constants.Projectiles.GetSpeed(type);
        float ySpeed = Addition.Constants.Projectiles.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *= -1;
        } else if (t.getY() > e.getY()) {
            ySpeed *= -1;
        }

        float rotate = 0;

        if (type == ARROW) {
            float arcVal = (float) Math.atan(yDist / (float) xDist);
            rotate = (float) Math.toDegrees(arcVal);
            if (xDist < 0) {
                rotate += 180;
            }
        }

        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));
    }

    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }
        return 0;
    }

    public void update() {
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                p.move();
                if (isProjectileHit(p)) {
                    p.setActive(false);
                }
            } else if(isProjectileOutsideOfBounds(p)) {
            	p.setActive(false);
            }
        }
    }

    private boolean isProjectileOutsideOfBounds(Projectile p) {
		if (p.getPos().x >= 0)
			if (p.getPos().x <= 640)
				if (p.getPos().y >= 0)
					if (p.getPos().y <= 800)
						return false;
		return true;
	}

	private boolean isProjectileHit(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if(e.isAlive()){
                if (e.getBounds().contains(p.getPos())) {
                    if(p.getProjectileType() == CHAINS){
                        e.slow();
                    }
                    e.hurt(p.getDmg());
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                if(p.getProjectileType() == ARROW){
                    g2d.translate(p.getPos().x, p.getPos().y);
                    g2d.rotate(Math.toRadians(p.getRotation()));
                    g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
                    g2d.rotate(-Math.toRadians(p.getRotation()));
                    g2d.translate(-p.getPos().x, -p.getPos().y);
                }
                else{
                    g2d.drawImage(proj_imgs[p.getProjectileType()], (int)p.getPos().x - 16, (int)p.getPos().y - 16, null);
                }
            }
        }
        
    }
    
    public void reset() {
    	projectiles.clear();
    	proj_id = 0;
    }
}
