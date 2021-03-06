<template>
    <div>
           <v-container class="py-0">
          <v-row align="center" justify="start">
            <v-col v-for="(selection, i) in selections" :key="selection.text" class="shrink">
              <v-chip :disabled="loading" close @click:close="selected.splice(i, 1)">
                <v-icon left v-text="selection.icon"></v-icon>
                {{ selection.text }}
              </v-chip>
            </v-col>

            <v-col v-if="!allSelected" cols="12">
              <v-text-field ref="search" v-model="search" full-width hide-details label="Search" single-line></v-text-field>
            </v-col>
          </v-row>
        </v-container>

        <v-divider v-if="!allSelected"></v-divider>

        <v-list>
          <template v-for="item in categories">
            <v-list-item v-if="!selected.includes(item)" :key="item.text" :disabled="loading" @click="selected.push(item)">
              <v-list-item-avatar>
                <v-icon :disabled="loading" v-text="item.icon"></v-icon>
              </v-list-item-avatar>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item>
          </template>
        </v-list>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!selected.length" :loading="loading" color="purple" text @click="next">
            Next
          </v-btn>
        </v-card-actions>
    </div>
</template>