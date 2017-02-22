package de.ellpeck.naturesaura.mod.particle;

import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleMagic extends Particle{

    public static final ResourceLocation RES_LOC = new ResourceLocation(ModUtil.MOD_ID, "particle/magic_round");

    public ParticleMagic(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int color, float scale, int maxAge, float gravity, boolean collision){
        super(world, posX, posY, posZ);
        this.particleScale = scale;
        this.particleMaxAge = maxAge;
        this.particleAlpha = 0F;
        this.canCollide = collision;
        this.particleGravity = gravity;

        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;

        this.setRBGColorF(((color >> 16) & 255)/255F, ((color >> 8) & 255)/255F, (color & 255)/255F);

        TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
        this.setParticleTexture(map.getAtlasSprite(RES_LOC.toString()));
    }

    @Override
    public void onUpdate(){
        super.onUpdate();

        float lifeRatio = (float)this.particleAge/(float)this.particleMaxAge;
        if(lifeRatio > 0.5F){
            this.particleAlpha = 1F-lifeRatio;
        }
        else{
            this.particleAlpha = lifeRatio;
        }
    }

    @Override
    public void renderParticle(VertexBuffer buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ){
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);

        ParticleHandler.particlesOnScreen++;
    }

    @Override
    public int getFXLayer(){
        return 1;
    }

    @Override
    public boolean isTransparent(){
        return true;
    }

    @Override
    public int getBrightnessForRender(float f){
        return 15 << 20 | 15 << 4;
    }
}
