// @ts-check
//
// The line above enables type checking for this file. Various IDEs interpret
// the @ts-check directive. It will give you helpful autocompletion when
// implementing this exercise.

// const list = ['a', 'b', 'c'];
// for (let i = 0; i < list.length; i++) {
//   // code that should be executed for each item list[i]
// }

/**
 * Calculates the total bird count.
 *
 * @param {number[]} birdsPerDay
 * @returns {number} total bird count
 */
export function totalBirdCount(birdsPerDay) {
  let sum = 0;
  for (let i = 0; i < birdsPerDay.length; i++) {
    sum += birdsPerDay[i];
  }
  return sum;
}

/**
 * Calculates the total number of birds seen in a specific week.
 *
 * @param {number[]} birdsPerDay
 * @param {number} week
 * @returns {number} birds counted in the given week
 */
export function birdsInWeek(birdsPerDay, week) {
  // It would be easier to take a slice of the array and just call totalBirdCount
  // But that's my functional programming brain talking :)
  // Let's do a for-loop as practice of different loop headers
  let sum = 0;
  let startingIndex = (week - 1) * 7;
  for (let i = startingIndex; i < startingIndex + 7; i++) {
    sum += birdsPerDay[i];
  }
  return sum;
}

/**
 * Fixes the counting mistake by increasing the bird count
 * by one for every second day.
 *
 * @param {number[]} birdsPerDay
 * @returns {number[]} corrected bird count data
 */
export function fixBirdCountLog(birdsPerDay) {
  for (let i = 0; i < birdsPerDay.length; i += 2) {
    birdsPerDay[i]++;
  }
  return birdsPerDay;
}
