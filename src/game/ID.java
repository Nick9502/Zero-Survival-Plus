package game;

/*
 * Every GameObject has an ID corresponding
 * to the type of object it is.
 */
public enum ID {
	Player(),
	BasicEnemy(),
	HardEnemy(),
	Trail(),
	Block(),
	FastEnemy(),
	SmartEnemy(),
	BossEnemy(),
	EnemyBullet(),
	MenuParticle();
}
