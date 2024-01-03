package dev.riftrealmsteam.riftrealmscore.item.custom;

import dev.riftrealmsteam.riftrealmscore.item.ModCreativeModeTab;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.awt.*;
import java.util.function.Consumer;

public class SpellBook extends Item implements IAnimatable, ISyncable {
    private static final int ACTIVATE_ANIM_STATE = 0;
    private static final AnimationBuilder ACTIVATE_ANIM = new AnimationBuilder().addAnimation("animation.spellbook.book_open", false);

    public final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public SpellBook(Properties properties) {
        super(properties);

        GeckoLibNetwork.registerSyncable(this);
    }

    public SpellBook() {
        super(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.TUTORIAL_TAB));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "Activation", 10, event -> PlayState.CONTINUE));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(stack , (ServerLevel)level);
            // Assign an ID for animations to this stack, then use it to sync an animation to the client
            GeckoLibNetwork.syncAnimation(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), this, id, ACTIVATE_ANIM_STATE);
        }

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ACTIVATE_ANIM_STATE) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "Activation");

//            if (controller.getAnimationState() == AnimationState.Stopped) {
//                final Player player = MyClientUtils.getPlayer();
//
//                if (player != null)
//                    player.sendSystemMessage(Component.literal("Activating my item!"));

                // If you don't do this, the popup animation will only play once because the
                // animation will be cached.
                controller.markNeedsReload();
                // Set the animation to open the JackInTheBoxItem which will start playing music
                // and
                // eventually do the actual animation. Also sets it to not loop
                controller.setAnimation(ACTIVATE_ANIM);
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new SpellBookRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }
}
