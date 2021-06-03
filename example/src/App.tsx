import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import SelectScreen from './SelectScreen';
import LoginScreen from './LoginScreen';

export type AppStackParamList = {
  SelectScreen: {};
  LoginScreen: {};
};
export interface TAppStackProps {}
const AppStack = createStackNavigator<AppStackParamList>();
const AppStackScreen = (_props: TAppStackProps) => (
  <AppStack.Navigator>
    <AppStack.Screen
      name={'SelectScreen'}
      component={SelectScreen}
      options={{ headerTitle: 'Examples' }}
    />
    <AppStack.Screen
      name={'LoginScreen'}
      component={LoginScreen}
      options={{ headerTitle: 'Login' }}
    />
  </AppStack.Navigator>
);

export default function App() {
  return (
    <NavigationContainer>
      <AppStackScreen />
    </NavigationContainer>
  );
}
