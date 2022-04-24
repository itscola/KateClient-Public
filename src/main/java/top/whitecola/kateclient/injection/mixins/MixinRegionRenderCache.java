package top.whitecola.kateclient.injection.mixins;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.client.renderer.RegionRenderCache.class)
public abstract class MixinRegionRenderCache {

    @Shadow protected abstract int getPositionIndex(BlockPos p_getPositionIndex_1_);

    @Shadow private IBlockState[] blockStates;

    @Shadow protected abstract IBlockState getBlockStateRaw(BlockPos p_getBlockStateRaw_1_);

    /**
     * @author White_cola
     * @reason fix the ArrayIndexOutOfBoundsException bug.
     */
    @Overwrite
    public IBlockState getBlockState(BlockPos p_getBlockState_1_) {

        int lvt_2_1_ = this.getPositionIndex(p_getBlockState_1_);
        IBlockState lvt_3_1_;
        try {
            lvt_3_1_ = this.blockStates[lvt_2_1_];
            if (lvt_3_1_ == null) {
                lvt_3_1_ = this.getBlockStateRaw(p_getBlockState_1_);
                this.blockStates[lvt_2_1_] = lvt_3_1_;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("lvt_2_1_ of blockStates[lvt_2_1_] is ArrayIndexOutOfBound. getBlockState(BlockPos p_getBlockState_1_) Will return default blockstate for now" );
            System.out.println("The block : "+p_getBlockState_1_.toString() );

            lvt_3_1_ = this.blockStates[this.blockStates.length-1];
            if(lvt_3_1_ == null){
                lvt_3_1_ = this.blockStates[0];
            }
        }
        return lvt_3_1_;
    }

}
