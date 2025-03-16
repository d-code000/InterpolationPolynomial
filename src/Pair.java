// Вспомогательная запись для кэша InterpolatingPolynomial.cache
// Представляет собой пару: начальный и конечный индексы точек при вычислении функции разделенной разности
public record Pair(Integer first, Integer second) {}