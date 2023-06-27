package chronosacaria.mcda.registries;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.entities.SummonedBeeEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;
import java.util.Map;

public class SummonedEntityRegistry {
    public static final Map<EntityType<? extends LivingEntity>, DefaultAttributeContainer> ATTRIBUTES =
            Maps.newHashMap();
    private static final List<EntityType<?>> SUMMONED_ENTITIES = Lists.newArrayList();

    public static final EntityType<SummonedBeeEntity> SUMMONED_BEE_ENTITY =
            FabricEntityTypeBuilder
                    .create(SpawnGroup.CREATURE, SummonedBeeEntity::new)
                    .dimensions(EntityDimensions.fixed(1,2))
                    .build();

    public static void register(){
        registerEntity("summoned_bee", SUMMONED_BEE_ENTITY);
    }

    public static void registerEntity(String name, EntityType<? extends LivingEntity> entity){
        Registry.register(Registries.ENTITY_TYPE, Mcda.ID(name), entity);
        ATTRIBUTES.put(entity, SummonedBeeEntity.getAttributeContainer());
    }
}
