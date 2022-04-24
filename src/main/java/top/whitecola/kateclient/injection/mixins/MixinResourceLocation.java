package top.whitecola.kateclient.injection.mixins;


import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(ResourceLocation.class)
public class MixinResourceLocation {
//    @Shadow protected String resourcePath;

    @Inject(method = "<init>(I[Ljava/lang/String;)V", at = @At(value = "RETURN"), cancellable = true)
    protected void ResourceLocation(int p_i45928_1_, String[] p_i45928_2_, CallbackInfo ci) {
//        if(this.resourcePath.startsWith("textures/gui/title/minecraft.png")){
//            this.resourcePath = "minecraft.png";
//        }
    }

}
