package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        Role result = roleStore.findById("111");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.add(new Role("1", "LAMER"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenReplaceThenRoleIsLamer() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.replace("1", new Role("1", "LAMER"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("LAMER");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.replace("111", new Role("1", "LAMER"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.delete("111");
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        boolean result = roleStore.replace("1", new Role("1", "UPPER_ADMIN"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        boolean result = roleStore.replace("111", new Role("1", "UPPER_ADMIN"));
        assertThat(result).isFalse();
    }

}