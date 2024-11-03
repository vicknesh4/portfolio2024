class MenuScene extends Phaser.Scene {
    constructor() {
        super({ key: 'MenuScene' });
    }

    create() {
        let titleText = this.add.text(window.innerWidth / 2, window.innerHeight / 2 - 100, 'OM Space Shooter', { fontSize: '48px', fill: '#fff' });
        titleText.setOrigin(0.5);

        let playButton = this.add.text(window.innerWidth / 2, window.innerHeight / 2, 'Jouer', { fontSize: '32px', fill: '#fff' });
        playButton.setOrigin(0.5);
        playButton.setInteractive();
        playButton.on('pointerdown', () => {
            this.scene.start('MainScene');
        });

        let quitButton = this.add.text(window.innerWidth / 2, window.innerHeight / 2 + 100, 'Quitter', { fontSize: '32px', fill: '#fff' });
        quitButton.setOrigin(0.5);
        quitButton.setInteractive();
        quitButton.on('pointerdown', () => {
            window.close();
        });
    }
}

class MainScene extends Phaser.Scene {
    constructor() {
        super({ key: 'MainScene' });
        this.level = 1;
        this.boss = null;
        this.bossHealth = 1000;
    }

    preload() {
        this.load.image('player', 'assets/om.png');
        this.load.image('bullet', 'assets/ballon.png');
        this.load.image('enemy', 'assets/psg.png');
        this.load.image('boss', 'assets/boss.png');
        this.load.image('background', 'assets/espace.png');
        this.load.spritesheet('explosion', 'assets/explosion.png', { frameWidth: 64, frameHeight: 64 });
    }

    create() {
        this.add.tileSprite(0, 0, window.innerWidth, window.innerHeight, 'background').setOrigin(0);
        this.player = this.physics.add.sprite(window.innerWidth / 2, window.innerHeight - 100, 'player').setDisplaySize(150, 150);
        this.player.setCollideWorldBounds(true);
        this.bossHealthText = this.add.text(window.innerWidth / 2, 50, 'Santé du Boss: 1300', { fontSize: '32px', fill: '#fff' }).setOrigin(0.5);
        this.cursors = this.input.keyboard.createCursorKeys();
        this.spaceKey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);
        this.bullets = this.physics.add.group({ defaultKey: 'bullet', maxSize: 10 });
        this.enemies = this.physics.add.group({ defaultKey: 'enemy', maxSize: 20 });
        this.physics.add.overlap(this.bullets, this.enemies, this.destroyEnemy, null, this);
        this.score = 0;
        this.enemySpeed = 100;
        this.scoreText = this.add.text(window.innerWidth / 2, 10, 'Score: 0', { fontSize: '32px', fill: '#fff' }).setOrigin(0.5);
        this.levelText = this.add.text(window.innerWidth - 100, 10, 'Niveau: 1', { fontSize: '32px', fill: '#fff' }).setOrigin(0.5);
        this.anims.create({
            key: 'explode',
            frames: this.anims.generateFrameNumbers('explosion', { start: 0, end: 23 }),
            frameRate: 20,
            repeat: 0,
            hideOnComplete: true
        });
        this.startLevel3();
    }

    startLevel1() {
        this.time.addEvent({ delay: 1000, callback: this.spawnEnemy, callbackScope: this, loop: true });
    }

    startLevel2() {
        this.enemySpeed = 200;
        this.time.addEvent({ delay: 800, callback: this.spawnEnemy, callbackScope: this, loop: true });
    }

    startLevel3() {
        this.boss = this.physics.add.sprite(Phaser.Math.Between(50, window.innerWidth - 50), 50, 'boss').setDisplaySize(300, 300);
        this.boss.setCollideWorldBounds(true);
        this.bossSpeed = 40;
        const finalYPosition = window.innerHeight - this.boss.displayHeight / 2;
        this.tweens.add({
            targets: this.boss,
            y: finalYPosition,
            duration: (finalYPosition - 50) / this.bossSpeed * 1000,
            ease: 'Linear',
            onComplete: () => {
                console.log('Le boss a atteint le bas de l\'écran');
            }
        });
        this.physics.add.overlap(this.bullets, this.boss, this.hitBoss, null, this);
        this.bossHealthBar = this.add.graphics();
        this.updateBossHealthBar();
    }

    updateBossHealthBar() {
        this.bossHealthBar.clear();
        const barWidth = 300;
        const barHeight = 20;
        const healthPercentage = this.bossHealth / 1300;
        this.bossHealthBar.fillStyle(0xff0000, 1);
        this.bossHealthBar.fillRect(this.boss.x - barWidth / 2, this.boss.y - this.boss.displayHeight / 2 - 30, barWidth, barHeight);
        this.bossHealthBar.fillStyle(0x00ff00, 1);
        this.bossHealthBar.fillRect(this.boss.x - barWidth / 2, this.boss.y - this.boss.displayHeight / 2 - 30, barWidth * healthPercentage, barHeight);
    }

    update() {
        if (this.cursors.left.isDown) {
            this.player.setVelocityX(-300);
        } else if (this.cursors.right.isDown) {
            this.player.setVelocityX(300);
        } else {
            this.player.setVelocityX(0);
        }

        if (Phaser.Input.Keyboard.JustDown(this.spaceKey)) {
            this.fireBullet();
        }

        this.bullets.children.iterate((bullet) => {
            if (bullet && bullet.y < 0) {
                bullet.setActive(false).setVisible(false);
            }
        });

        this.enemies.children.iterate((enemy) => {
            if (enemy && enemy.y > window.innerHeight) {
                enemy.setActive(false).setVisible(false).setY(50);
            }
        });

        if (this.boss) {
            this.updateBossHealthBar();
        }

        if (this.level === 1 && this.score >= 1000) {
            this.levelUp();
        } else if (this.level === 2 && this.score >= 2000) {
            this.levelUp();
        }

        if (this.level === 3 && this.bossHealth <= 0) {
            this.winGame();
        }
    }

    fireBullet() {
        let bullet = this.bullets.get(this.player.x, this.player.y - 20);
        if (bullet) {
            bullet.setActive(true).setVisible(true).setVelocityY(-400).setDisplaySize(50, 50);
        }
    }

    spawnEnemy() {
        if (this.level < 3) {
            let enemy = this.enemies.get(Phaser.Math.Between(50, window.innerWidth - 50), 50);
            if (enemy) {
                enemy.setActive(true).setVisible(true).setVelocityY(this.enemySpeed).setDisplaySize(100, 100);
            }
        }
    }

    destroyEnemy(bullet, enemy) {
        if (!bullet.active || !enemy.active) {
            return;
        }
        bullet.setActive(false).setVisible(false).setVelocity(0, 0);
        enemy.setActive(false).setVisible(false);
        let explosion = this.add.sprite(enemy.x, enemy.y, 'explosion').setDisplaySize(50, 50);
        explosion.play('explode');
        this.score += 50;
        this.scoreText.setText('Score: ' + this.score);
    }

    hitBoss(bullet, boss) {
        bullet.destroy();
        this.bossHealth -= 50;
        this.updateBossHealthBar();
        if (this.bossHealth <= 0) {
            this.boss.destroy();
            this.bossHealthBar.setVisible(false);
            console.log('Le boss a été vaincu!');
            this.winGame();
        }
    }

    levelUp() {
        this.level += 1;
        this.levelText.setText('Niveau: ' + this.level);
        if (this.level === 2) {
            this.startLevel2();
        } else if (this.level === 3) {
            this.startLevel3();
        }
    }

    winGame() {
        this.add.text(window.innerWidth / 2, window.innerHeight / 2, 'Victoire !', { fontSize: '64px', fill: '#fff' }).setOrigin(0.5);
        this.scene.pause();
    }
}

let config = {
    type: Phaser.AUTO,
    width: window.innerWidth,
    height: window.innerHeight,
    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 0 },
            debug: false
        }
    },
    scene: [MenuScene, MainScene]
};

let game = new Phaser.Game(config);

window.addEventListener('resize', () => {
    game.scale.resize(window.innerWidth, window.innerHeight);
    game.scene.resize();
});
