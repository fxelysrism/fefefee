package com.insanecraft.core.material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Registry for new materials.
 */
public class MaterialRegistry {
    private final List<MaterialDefinition> materials = new ArrayList<>();

    public void register(MaterialDefinition material) {
        materials.add(material);
    }

    public List<MaterialDefinition> getMaterials() {
        return List.copyOf(materials);
    }

    public Optional<MaterialDefinition> findById(String id) {
        for (MaterialDefinition material : materials) {
            if (material.getId().equals(id)) {
                return Optional.of(material);
            }
        }
        return Optional.empty();
    }
}
