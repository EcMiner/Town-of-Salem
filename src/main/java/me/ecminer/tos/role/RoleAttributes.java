package me.ecminer.tos.role;

public enum RoleAttributes implements RoleAttribute {

    /**
     * Night immunity means you can't be killed at night unless has {@link #IGNORE_NIGHT_IMMUNE ignore night immunity}
     * attribute
     */
    NIGHT_IMMUNE,
    /**
     * Roleblock immunity means that you can't be roleblocked, i.e. by escort.
     */
    ROLE_BLOCK_IMMUNE,
    /**
     * Bite immune means you can't be bitten by a vampire
     */
    BITE_IMMUNE,
    /**
     * Detection immunity means you can't be detected by the sheriff (doesn't work against town investigatives or the
     * consigliere)
     */
    DETECTION_IMMUNE,
    /**
     * Control immunity means you can't be controlled by the witch, any roles without a night ability are control
     * immune
     */
    CONTROL_IMMUNE,
    /**
     * Ignore night immunity means a role with {@link #NIGHT_IMMUNE night immunity} will not be protected by his night
     * immunity
     */
    IGNORE_NIGHT_IMMUNE;

}
