package net.combatroll.mixin;

import net.combatroll.CombatRoll;
import net.combatroll.internals.RollManager;
import net.combatroll.internals.RollingEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin implements RollingEntity {
    private RollManager rollManager = new RollManager();
    public RollManager getRollManager() {
        return rollManager;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick_TAIL(CallbackInfo ci) {
        var player = (ClientPlayerEntity) ((Object)this);
        if (player != null) {
            rollManager.tick(player);
        }
    }

    @Shadow
    @Final
    protected MinecraftClient client;

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;tick(ZF)V", shift = At.Shift.AFTER))
    private void tickMovement_ModifyInput(CallbackInfo ci) {
        var clientPlayer = (ClientPlayerEntity) ((Object) this);
        var config = CombatRoll.config;
        if (!config.allow_jump_while_rolling && rollManager.isRolling()) {
            clientPlayer.input.jumping = false;
        }
    }

    @Inject(method = "shouldAutoJump", at = @At("HEAD"), cancellable = true)
    public void shouldAutoJump_HEAD(CallbackInfoReturnable<Boolean> cir) {
        var config = CombatRoll.config;
        if (config != null) {
            if (rollManager.isRolling() && !config.allow_auto_jump_while_rolling) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}