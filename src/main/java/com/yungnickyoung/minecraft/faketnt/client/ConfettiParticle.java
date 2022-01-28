package com.yungnickyoung.minecraft.faketnt.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ConfettiParticle extends TextureSheetParticle {
    ConfettiParticle(ClientLevel clientLevel, double xo, double yo, double zo) {
        super(clientLevel, xo, yo, zo, 0.0, 0.0, 0.0);
        this.gravity = .05f;
        this.friction = 0.7f; // acceleration
        this.xd *= random.nextFloat() * 6f + 1f;
        this.zd *= random.nextFloat() * 6f + 1f;
        this.yd *= random.nextFloat() * 6f;
        this.quadSize *= this.random.nextFloat() * 1.4f + 0.2f;
        this.lifetime = (int)(20.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getLightColor(float f) {
        int lightAtPos = super.getLightColor(f);
        int k = lightAtPos >> 16 & 0xFF;
        return 0xF0 | k << 16;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double xo, double yo, double zo, double dx, double dy, double dz){
            ConfettiParticle confettiParticle = new ConfettiParticle(clientLevel, xo, yo, zo);
            confettiParticle.pickSprite(this.sprite);
            return confettiParticle;
        }
    }
}
