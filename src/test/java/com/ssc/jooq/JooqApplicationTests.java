package com.ssc.jooq;

import org.jooq.lambda.Seq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.jooq.lambda.tuple.Tuple.tuple;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class JooqApplicationTests {

	@Test
	public void concat() {
		System.out.println("concat > " +
				Seq.of(1,2,3).concat(Seq.of(4,5,6)));
		/**
		 * concat > 123456
		 */
	}

	@Test
	public void contains() {
		System.out.println("contains > " +
				Seq.of(1,2,3).contains(3));
		/**
		 * contains > true
		 */
	}

	@Test
	public void containsAll() {
		System.out.println("containsAll > " +
				Seq.of(1,2,3).containsAll(3,1));
		/**
		 * containsAll > true
		 */
	}

	@Test
	public void containsAny() {
		System.out.println("containsAny > " +
				Seq.of(1,2,3).containsAny(3,44,5));
		/**
		 * containsAny > true
		 */
	}

	@Test
	public void crossJoin() {
		System.out.println("crossJoin > " +
				Seq.of(1,2,3).crossJoin(Seq.of(3,5,6)));
		/**
		 * crossJoin > (1, 3)(1, 5)(1, 6)(2, 3)(2, 5)(2, 6)(3, 3)(3, 5)(3, 6)
		 */
	}

	@Test
	public void crossSelfJoin() {
		System.out.println("crossSelfJoin > " +
				Seq.of(1,2,3).crossSelfJoin());
		/**
		 * crossSelfJoin > (1, 1)(1, 2)(1, 3)(2, 1)(2, 2)(2, 3)(3, 1)(3, 2)(3, 3)
		 */
	}

	@Test
	public void cycle() {
		System.out.println("cycle > " +
				Seq.of(1).cycle(9));
		/**
		 * cycle > 111111111
		 */
	}

	@Test
	public void duplicate() {
		System.out.println("duplicate > " +
				Seq.of(1,2).duplicate());
		/**
		 * duplicate > (12, 12)
		 */
	}

	@Test
	public void foldLeft() {
		System.out.println("foldLeft > " +
				Seq.of("a", "b", "c").foldLeft("-->", (u,v) -> {
					System.out.println("u: " + u);
					System.out.println("v: " + v);
					System.out.println("u + v: " + (u + v));
					System.out.println("---");
					return u + v;}));
		/**
		 * u: -->
		 * v: a
		 * u + v: -->a
		 * ---
		 * u: -->a
		 * v: b
		 * u + v: -->ab
		 * ---
		 * u: -->ab
		 * v: c
		 * u + v: -->abc
		 * ---
		 * foldLeft > -->abc
		 */
	}

	@Test
	public void foldRight() {
		System.out.println("foldRight > " +
				Seq.of("a", "b", "c").foldRight("<--", (u,v) -> {
					System.out.println("u: " + u);
					System.out.println("v: " + v);
					System.out.println("u + v: " + (u + v));
					System.out.println("---");
					return u + v;}));
		/**
		 * u: c
		 * v: <--
		 * u + v: c<--
		 * ---
		 * u: b
		 * v: c<--
		 * u + v: bc<--
		 * ---
		 * u: a
		 * v: bc<--
		 * u + v: abc<--
		 * ---
		 * foldRight > abc<--
		 */
	}

	@Test
	public void groupBy() {
		System.out.println("groupBy > " +
				Seq.of(1,2,3,4).groupBy( i -> i % 2));
		/**
		 * groupBy > {0=[2, 4], 1=[1, 3]}
		 */
	}

	@Test
	public void grouped() {
		System.out.println("grouped > " +
				Seq.of(1, 2, 3, 4).grouped(i -> i % 2));
		/**
		 * grouped > (1, 13)(0, 24)
		 */
	}

	@Test
	public void innerJoin() {
		System.out.println("innerJoin > " +
				Seq.of(1, 2, 4).innerJoin(Seq.of(4, 1, 2, 3), (a, b) -> a == b));
		/**
		 * innerJoin > (1, 1)(2, 2)(4, 4)
		 */
	}

	@Test
	public void innerSelfJoin() {
		System.out.println("innerSelfJoin > " +
				Seq.of(1, 2).innerSelfJoin((t, u) -> t != u));
		/**
		 * innerSelfJoin > (1, 2)(2, 1)
		 */

		System.out.println("innerSelfJoin > " +
				Seq.of(1, 2).innerSelfJoin((t, u) -> t == u));
		/**
		 * innerSelfJoin > (1, 1)(2, 2)
		 */
	}

	@Test
	public void intersperse() {
		System.out.println("intersperse > " +
				Seq.of(1, 2, 3, 4).intersperse(0));
		/**
		 * intersperse > 1020304
		 */
	}

	@Test
	public void join() {
		System.out.println("join > " +
				Seq.of(1, 2, 3).join());
		/**
		 * join > 123
		 */

		System.out.println("join > " +
				Seq.of(1, 2, 3).join(", "));
		/**
		 * join > 1, 2, 3
		 */

		System.out.println("join > " +
				Seq.of(1, 2, 3).join("|", "^", "$"));
		/**
		 * join > ^1|2|3$
		 */
	}

	@Test
	public void leftOuterJoin() {
		System.out.println("leftOuterJoin > " +
				Seq.of(1, 2, 4).leftOuterJoin(
						Seq.of(1, 2, 3),
						(a, b) -> {
							System.out.println("a: " + a + " b: " + b + " a == b ? " + (a == b));
							System.out.println("---");
							return a == b;
						}));
		/**
		 * a: 1 b: 1 a == b ? true
		 * ---
		 * a: 1 b: 2 a == b ? false
		 * ---
		 * a: 1 b: 3 a == b ? false
		 * ---
		 * a: 2 b: 1 a == b ? false
		 * ---
		 * a: 2 b: 2 a == b ? true
		 * ---
		 * a: 2 b: 3 a == b ? false
		 * ---
		 * a: 4 b: 1 a == b ? false
		 * ---
		 * a: 4 b: 2 a == b ? false
		 * ---
		 * a: 4 b: 3 a == b ? false
		 * ---
		 * leftOuterJoin > (1, 1)(2, 2)(4, null)
		 */
	}

	@Test
	public void leftOuterSelfJoin() {
		System.out.println("leftOuterSelfJoin > " +
				Seq.of(
						tuple(1, 0),
						tuple(2, 1))
						.leftOuterSelfJoin(
								(t, u) -> {
									System.out.println("t.v1 : " + t.v1 + " t.v2 : " + t.v2);
									System.out.println("u.v1 : " + u.v1 + " u.v2 : " + u.v2);
									System.out.println("t.v2 == u.v1 :: " + (t.v2 == u.v1));
									System.out.println("---");
									return t.v2 == u.v1; }));
		/**
		 * t.v1 : 1 t.v2 : 0
		 * u.v1 : 1 u.v2 : 0
		 * t.v2 == u.v1 :: false
		 * ---
		 * t.v1 : 1 t.v2 : 0
		 * u.v1 : 2 u.v2 : 1
		 * t.v2 == u.v1 :: false
		 * ---
		 * t.v1 : 2 t.v2 : 1
		 * u.v1 : 1 u.v2 : 0
		 * t.v2 == u.v1 :: true
		 * ---
		 * t.v1 : 2 t.v2 : 1
		 * u.v1 : 2 u.v2 : 1
		 * t.v2 == u.v1 :: false
		 * ---
		 * leftOuterSelfJoin > ((1, 0), null)((2, 1), (1, 0))
		 */
	}

	@Test
	public void limitWhile() {
		System.out.println("limitWhile > " +
				Seq.of(1, 2, 3, 4, 5).limitWhile(i -> i < 3));
		/**
		 * limitWhile > 12
		 */
	}

	@Test
	public void limitUntil() {
		System.out.println("limitUntil > " +
				Seq.of(1, 2, 3, 4, 5).limitUntil(i -> i == 3));
		/**
		 * limitUntil > 12
		 */
	}

	@Test
	public void ofType() {
		System.out.println("ofType > " +
				Seq.of(new Object(), 1, "B", 2L).ofType(Number.class));
		/**
		 * ofType > 12
		 */
	}

	@Test
	public void rightOuterJoin() {
		System.out.println("rightOuterJoin > " +
				Seq.of(1, 2, 4).rightOuterJoin(Seq.of(1, 2, 3), (a, b) ->
				{
					System.out.println("a: " + a + " b: " + b + " a == b ? " + (a == b));
					System.out.println("---");
					return a == b;
				}));
		/**
		 * a: 1 b: 1 a == b ? true
		 * ---
		 * a: 2 b: 1 a == b ? false
		 * ---
		 * a: 4 b: 1 a == b ? false
		 * ---
		 * a: 1 b: 2 a == b ? false
		 * ---
		 * a: 2 b: 2 a == b ? true
		 * ---
		 * a: 4 b: 2 a == b ? false
		 * ---
		 * a: 1 b: 3 a == b ? false
		 * ---
		 * a: 2 b: 3 a == b ? false
		 * ---
		 * a: 4 b: 3 a == b ? false
		 * ---
		 * rightOuterJoin > (1, 1)(2, 2)(null, 3)
		 */
	}

	@Test
	public void rightOuterSelfJoin() {
		System.out.println("rightOuterSelfJoin > " +
				Seq.of(
						tuple(1, 0),
						tuple(2, 1))
						.rightOuterSelfJoin((t, u) -> {
							System.out.println("t.v1 : " + t.v1 + " t.v2 : " + t.v2);
							System.out.println("u.v1 : " + u.v1 + " u.v2 : " + u.v2);
							System.out.println("t.v2 == u.v1 :: " + (t.v1 == u.v2));
							System.out.println("---");
							return t.v2 == u.v1; }));
		/**
		 * t.v1 : 1 t.v2 : 0
		 * u.v1 : 1 u.v2 : 0
		 * t.v2 == u.v1 :: false
		 * ---
		 * t.v1 : 2 t.v2 : 1
		 * u.v1 : 1 u.v2 : 0
		 * t.v2 == u.v1 :: false
		 * ---
		 * t.v1 : 1 t.v2 : 0
		 * u.v1 : 2 u.v2 : 1
		 * t.v2 == u.v1 :: true
		 * ---
		 * t.v1 : 2 t.v2 : 1
		 * u.v1 : 2 u.v2 : 1
		 * t.v2 == u.v1 :: false
		 * ---
		 * rightOuterSelfJoin > ((2, 1), (1, 0))(null, (2, 1))
		 */
	}

	@Test
	public void partition() {
		System.out.println("partition > " +
				Seq.of(1, 2, 3, 4).partition(i -> i % 2 != 0));
		/**
		 * partition > (13, 24)
		 */
	}

	@Test
	public void remove() {
		System.out.println("remove > " +
				Seq.of(1, 2, 3, 4).remove(2));
		/**
		 * remove > 134
		 */
	}

	@Test
	public void removeAll() {
		System.out.println("removeAll > " +
				Seq.of(1, 2, 3, 4).removeAll(2, 3, 5));
		/**
		 * removeAll > 14
		 */
	}

	@Test
	public void retainAll() {
		System.out.println("retainAll > " +
				Seq.of(1, 2, 3, 4).retainAll(2, 3, 5));
		/**
		 * retainAll > 23
		 */
	}

	@Test
	public void reverse() {
		System.out.println("reverse > " +
				Seq.of(1, 2, 3, 4).reverse());
		/**
		 * reverse > 4321
		 */
	}

	@Test
	public void shuffle() {
		System.out.println("shuffle > " +
				Seq.of(1, 2, 3, 4, 5).shuffle());
		/**
		 * shuffle > 35412
		 */
	}

	@Test
	public void skipWhile() {
		System.out.println("skipWhile > " +
				Seq.of(1, 2, 3, 4, 5).skipWhile(i -> i < 3));
		/**
		 * skipWhile > 345
		 */
	}

	@Test
	public void skipUntil() {
		System.out.println("skipUntil > " +
				Seq.of(1, 2, 3, 4, 5).skipUntil(i -> i == 3));
		/**
		 * skipUntil > 345
		 */
	}

	@Test
	public void slice() {
		System.out.println("slice > " +
				Seq.of(1, 2, 3, 4, 5).slice(1, 3));
		/**
		 * slice > 23
		 */
	}

	@Test
	public void splitAt() {
		System.out.println("splitAt > " +
				Seq.of(1, 2, 3, 4, 5).splitAt(2));
		/**
		 * splitAt > (12, 345)
		 */
	}

	@Test
	public void splitAtHead() {
		System.out.println("splitAtHead > " +
				Seq.of(1, 2, 3, 4, 5).splitAtHead());
		/**
		 * splitAtHead > (Optional[1], 2345)
		 */
	}

	@Test
	public void unzip() {
		System.out.println("unzip > " +
				Seq.unzip(Seq.of(tuple(1, "a"), tuple(2, "b"), tuple(3, "c"))));
		/**
		 * unzip > (123, abc)
		 */
	}

	@Test
	public void zip() {
		System.out.println("zip > " +
				Seq.of(1, 2, 3).zip(Seq.of("a", "b", "c")));
		/**
		 * zip > (1, a)(2, b)(3, c)
		 */

		System.out.println("zip > " +
				Seq.of(1, 2, 3).zip(Seq.of("a", "b", "c"), (x, y) -> x + ":" + y));
		/**
		 * zip > 1:a2:b3:c
		 */
	}

	@Test
	public void zipWithIndex() {
		System.out.println("zipWithIndex > " +
				Seq.of("a", "b", "c").zipWithIndex());
		/**
		 * zipWithIndex > (a, 0)(b, 1)(c, 2)
		 */
	}

	@Test
	void contextLoads() {

	}

}
