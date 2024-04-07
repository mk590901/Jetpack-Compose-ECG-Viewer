package com.widget.listcomposecard.core

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import android.util.Log
import android.util.Size
import java.util.Collections

class StoreWrapper(//  Number of displayed drawable data pieces
    val _seriesLength: Int, //  Data buffer size
    val _seriesNumber: Int, //  Mode
    private var _mode: GraphMode
) {
    val TAG = StoreWrapper::class.java.simpleName
    var rowData: List<Int?> = ArrayList(
        mutableListOf<Int?>(
            1874,
            1866,
            1843,
            1832,
            1818,
            1801,
            1796,
            1781,
            1764,
            1752,
            1744,
            1731,
            1721,
            1715,
            1714,
            1709,
            1712,
            1703,
            1715,
            1723,
            1725,
            1728,
            1729,
            1726,
            1730,
            1732,
            1723,
            1736,
            1727,
            1726,
            1734,
            1731,
            1739,
            1738,
            1735,
            1739,
            1741,
            1741,
            1738,
            1744,
            1746,
            1743,
            1739,
            1742,
            1744,
            1749,
            1752,
            1753,
            1756,
            1756,
            1751,
            1751,
            1755,
            1753,
            1751,
            1758,
            1751,
            1758,
            1758,
            1755,
            1760,
            1759,
            1766,
            1768,
            1771,
            1774,
            1771,
            1776,
            1777,
            1770,
            1782,
            1781,
            1781,
            1785,
            1787,
            1779,
            1787,
            1775,
            1779,
            1787,
            1794,
            1787,
            1793,
            1790,
            1794,
            1788,
            1788,
            1791,
            1791,
            1790,
            1796,
            1791,
            1796,
            1801,
            1796,
            1793,
            1802,
            1800,
            1799,
            1800,
            1806,
            1808,
            1809,
            1811,
            1807,
            1808,
            1808,
            1805,
            1803,
            1807,
            1807,
            1809,
            1815,
            1814,
            1811,
            1818,
            1811,
            1813,
            1812,
            1807,
            1816,
            1812,
            1814,
            1813,
            1816,
            1817,
            1821,
            1821,
            1824,
            1832,
            1827,
            1841,
            1852,
            1855,
            1874,
            1888,
            1894,
            1912,
            1926,
            1939,
            1957,
            1964,
            1967,
            1983,
            1991,
            1990,
            1998,
            2005,
            2008,
            2005,
            2011,
            2006,
            2006,
            2001,
            1994,
            1984,
            1974,
            1959,
            1954,
            1937,
            1926,
            1918,
            1902,
            1885,
            1884,
            1863,
            1848,
            1837,
            1832,
            1815,
            1813,
            1808,
            1810,
            1811,
            1815,
            1813,
            1821,
            1820,
            1824,
            1819,
            1824,
            1828,
            1815,
            1831,
            1827,
            1828,
            1826,
            1831,
            1826,
            1824,
            1829,
            1827,
            1829,
            1836,
            1834,
            1833,
            1829,
            1830,
            1833,
            1835,
            1840,
            1836,
            1843,
            1844,
            1839,
            1843,
            1839,
            1848,
            1830,
            1799,
            1757,
            1708,
            1652,
            1607,
            1588,
            1595,
            1654,
            1743,
            1860,
            2016,
            2190,
            2371,
            2553,
            2735,
            2907,
            3051,
            3157,
            3198,
            3167,
            3086,
            2941,
            2758,
            2569,
            2371,
            2166,
            1996,
            1845,
            1732,
            1659,
            1611,
            1586,
            1576,
            1581,
            1612,
            1649,
            1697,
            1737,
            1769,
            1789,
            1796,
            1792,
            1788,
            1780,
            1772,
            1768,
            1776,
            1774,
            1775,
            1779,
            1775,
            1775,
            1780,
            1779,
            1782,
            1778,
            1779,
            1777,
            1782,
            1779,
            1791,
            1783,
            1782,
            1786,
            1781,
            1789,
            1789,
            1790,
            1790,
            1789,
            1792,
            1791,
            1795,
            1785,
            1792,
            1792,
            1796,
            1793,
            1791,
            1798,
            1797,
            1798,
            1802,
            1796,
            1798,
            1800,
            1800,
            1803,
            1808,
            1810,
            1812,
            1804,
            1807,
            1808,
            1806,
            1806,
            1808,
            1811,
            1823,
            1835,
            1851,
            1865,
            1885,
            1894,
            1903,
            1924,
            1936,
            1945,
            1955,
            1975,
            1983,
            1999,
            2016,
            2030,
            2039,
            2047,
            2051,
            2069,
            2077,
            2091,
            2104,
            2114,
            2123,
            2130,
            2132,
            2139,
            2138,
            2150,
            2153,
            2160,
            2164,
            2165,
            2163,
            2165,
            2168,
            2164,
            2165,
            2161,
            2164,
            2158,
            2151,
            2142,
            2139,
            2134,
            2117,
            2117,
            2103,
            2091,
            2082,
            2070,
            2062,
            2049,
            2041,
            2024,
            2009,
            2000,
            1990,
            1973,
            1958,
            1944,
            1936,
            1920,
            1901,
            1885,
            1874,
            1851,
            1845,
            1831,
            1819,
            1808,
            1787,
            1773,
            1763,
            1748,
            1736,
            1728,
            1718,
            1718,
            1711,
            1716,
            1719,
            1718,
            1721,
            1719,
            1723,
            1722,
            1726,
            1726,
            1729,
            1723,
            1730,
            1736,
            1727,
            1730,
            1736,
            1734,
            1727,
            1736,
            1731,
            1737,
            1740,
            1742,
            1745,
            1744,
            1745,
            1749,
            1743,
            1746,
            1751,
            1748,
            1754,
            1755,
            1758,
            1751,
            1753,
            1756,
            1759,
            1757,
            1762,
            1759,
            1759,
            1768,
            1768,
            1768,
            1770,
            1767,
            1766,
            1766,
            1772,
            1771,
            1767,
            1768,
            1768,
            1770,
            1771,
            1775,
            1774,
            1778,
            1772,
            1780,
            1782,
            1783,
            1779,
            1792,
            1785,
            1784,
            1781,
            1790,
            1787,
            1797,
            1789,
            1797,
            1795,
            1791,
            1796,
            1789,
            1795,
            1790,
            1794,
            1795,
            1800,
            1797,
            1791,
            1802,
            1804,
            1797,
            1803,
            1800,
            1805,
            1806,
            1810,
            1812,
            1807,
            1804,
            1814,
            1807,
            1808,
            1808,
            1808,
            1816,
            1814,
            1815,
            1815,
            1812,
            1811,
            1812,
            1811,
            1812,
            1822,
            1822,
            1818,
            1826,
            1825,
            1830,
            1830,
            1839,
            1851,
            1863,
            1877,
            1886,
            1905,
            1916,
            1935,
            1948,
            1958,
            1969,
            1971,
            1982,
            1992,
            1999,
            2007,
            2012,
            2012,
            2009,
            2009,
            2008,
            1996,
            1983,
            1980,
            1970,
            1965,
            1956,
            1947,
            1934,
            1921,
            1909,
            1899,
            1884,
            1871,
            1864,
            1845,
            1837,
            1828,
            1811,
            1811,
            1808,
            1809,
            1806,
            1808,
            1816,
            1818,
            1816,
            1822,
            1822,
            1824,
            1826,
            1821,
            1824,
            1826,
            1822,
            1825,
            1826,
            1832,
            1827,
            1838,
            1836,
            1830,
            1829,
            1830,
            1832,
            1833,
            1838,
            1833,
            1832,
            1829,
            1835,
            1829,
            1836,
            1834,
            1827,
            1827,
            1818,
            1804,
            1773,
            1716,
            1672,
            1622,
            1593,
            1585,
            1626,
            1703,
            1812,
            1943,
            2111,
            2304,
            2478,
            2666,
            2837,
            3004,
            3118,
            3186,
            3180,
            3118,
            2989,
            2832,
            2638,
            2441,
            2243,
            2055,
            1896,
            1766,
            1680,
            1616,
            1591,
            1574,
            1578,
            1591,
            1629,
            1675,
            1720,
            1751,
            1772,
            1781,
            1786,
            1789,
            1785,
            1779,
            1776,
            1769,
            1767,
            1767,
            1772,
            1771,
            1771,
            1771,
            1780,
            1780,
            1778,
            1777,
            1775,
            1774,
            1775,
            1774,
            1775,
            1784,
            1784,
            1788,
            1786,
            1791,
            1786,
            1786,
            1786,
            1786,
            1787,
            1785,
            1790,
            1794,
            1791,
            1791,
            1786,
            1788,
            1792,
            1793,
            1795,
            1800,
            1795,
            1803,
            1803,
            1800,
            1804,
            1808,
            1804,
            1805,
            1803,
            1802,
            1806,
            1806,
            1808,
            1810,
            1812,
            1820,
            1829,
            1838,
            1855,
            1869,
            1878,
            1896,
            1920,
            1930,
            1938,
            1951,
            1963,
            1978,
            1990,
            2005,
            2016,
            2030,
            2044,
            2060,
            2067,
            2077,
            2082,
            2094,
            2100,
            2106,
            2120,
            2121,
            2135,
            2141,
            2146,
            2143,
            2152,
            2155,
            2158,
            2159,
            2159,
            2159,
            2158,
            2162,
            2156,
            2154,
            2143,
            2146,
            2145,
            2136,
            2133,
            2125,
            2116,
            2106,
            2095,
            2088,
            2074,
            2059,
            2048,
            2031,
            2022,
            2015,
            2001,
            1988,
            1972,
            1955,
            1939,
            1927,
            1914,
            1897,
            1883,
            1869,
            1853,
            1844,
            1832,
            1815,
            1803,
            1790,
            1774,
            1765,
            1750,
            1735,
            1726,
            1718,
            1720,
            1715,
            1713,
            1714,
            1720,
            1728,
            1725,
            1727,
            1727,
            1731,
            1730,
            1728,
            1732,
            1726,
            1727,
            1728,
            1734,
            1734,
            1739,
            1741,
            1743,
            1742,
            1748,
            1742,
            1744,
            1742,
            1743,
            1748,
            1750,
            1749,
            1743,
            1755,
            1750,
            1754,
            1747,
            1753,
            1756,
            1753,
            1755,
            1761,
            1760,
            1759,
            1755,
            1759,
            1759,
            1764,
            1758,
            1758,
            1768,
            1767,
            1767,
            1772,
            1768,
            1769,
            1771,
            1781,
            1777,
            1784,
            1775,
            1775,
            1776,
            1783,
            1775,
            1775,
            1774,
            1772,
            1784,
            1791,
            1788,
            1785,
            1790,
            1788,
            1784,
            1783,
            1787,
            1783,
            1789,
            1791,
            1792,
            1787,
            1794,
            1793,
            1790,
            1793,
            1783,
            1789,
            1799,
            1791,
            1798,
            1797,
            1797,
            1800,
            1797,
            1795,
            1803,
            1806,
            1809,
            1808,
            1810,
            1814,
            1814,
            1812,
            1814,
            1816,
            1810,
            1812,
            1814,
            1815,
            1820,
            1817,
            1824,
            1821,
            1818,
            1820,
            1820,
            1818,
            1824,
            1824,
            1832,
            1846,
            1853,
            1875,
            1887,
            1899,
            1912,
            1924,
            1937,
            1947,
            1961,
            1967,
            1978,
            1985,
            1996,
            1998,
            2004,
            2002,
            2002,
            2000,
            2000,
            1997,
            2000,
            1989,
            1978,
            1971,
            1959,
            1947,
            1939,
            1928,
            1912,
            1900,
            1889,
            1868,
            1862,
            1851,
            1837,
            1826,
            1821,
            1817,
            1814,
            1819,
            1821,
            1818,
            1817,
            1824,
            1818,
            1819,
            1819,
            1819,
            1824,
            1825,
            1830,
            1827,
            1829,
            1823,
            1828,
            1826,
            1829,
            1831,
            1829,
            1834,
            1830,
            1832,
            1831,
            1836,
            1832,
            1829,
            1833,
            1834,
            1838,
            1839,
            1836,
            1838,
            1837,
            1832,
            1819,
            1807,
            1784,
            1739,
            1689,
            1635,
            1599,
            1580,
            1603,
            1663,
            1754,
            1889,
            2049,
            2223,
            2409,
            2591,
            2778,
            2940,
            3077,
            3166,
            3183,
            3139,
            3040,
            2893,
            2712,
            2523,
            2316,
            2123,
            1958,
            1808,
            1713,
            1643,
            1603,
            1579,
            1578,
            1590,
            1619,
            1664,
            1705,
            1744,
            1772,
            1786,
            1792,
            1783,
            1779,
            1774,
            1769,
            1770,
            1767,
            1773,
            1773,
            1768,
            1773,
            1772,
            1771,
            1775,
            1784,
            1781,
            1783,
            1788,
            1786,
            1784,
            1776,
            1783,
            1784,
            1786,
            1784,
            1785,
            1788,
            1782,
            1783,
            1792,
            1789,
            1787,
            1783,
            1793,
            1791,
            1797,
            1802,
            1793,
            1806,
            1802,
            1801,
            1800,
            1808,
            1804,
            1808,
            1804,
            1803,
            1804,
            1802,
            1803,
            1802,
            1801,
            1808,
            1808,
            1807,
            1814,
            1824,
            1826,
            1827,
            1842,
            1859,
            1869,
            1883,
            1905,
            1910,
            1927,
            1944,
            1957,
            1968,
            1979,
            1989,
            2007,
            2025,
            2040,
            2056,
            2059,
            2073,
            2083,
            2091,
            2102,
            2102,
            2115,
            2121,
            2131,
            2140,
            2147,
            2152,
            2155,
            2163,
            2163,
            2168,
            2168,
            2167,
            2169,
            2168,
            2165,
            2163,
            2160,
            2160,
            2155,
            2143,
            2134,
            2132,
            2128,
            2118,
            2109,
            2099,
            2096,
            2078,
            2070,
            2055,
            2048,
            2027,
            2015,
            2002,
            1996,
            1979,
            1969,
            1955,
            1946,
            1923,
            1912,
            1896,
            1882,
            1863,
            1845,
            1831,
            1822,
            1806,
            1795,
            1782,
            1769,
            1752,
            1741,
            1730,
            1720,
            1713,
            1715,
            1717,
            1713,
            1717,
            1714,
            1717,
            1720,
            1726,
            1729,
            1724,
            1723,
            1732,
            1735,
            1729,
            1737,
            1736,
            1732,
            1736,
            1734,
            1744,
            1744,
            1739,
            1746,
            1746,
            1745,
            1752,
            1750,
            1749,
            1751,
            1744,
            1748,
            1749,
            1747,
            1756,
            1757,
            1761,
            1754,
            1759,
            1756,
            1761,
            1763,
            1765,
            1770,
            1771,
            1765,
            1770,
            1770,
            1771,
            1766,
            1767,
            1774,
            1773,
            1776,
            1775,
            1771,
            1781,
            1777,
            1773,
            1774,
            1775,
            1780,
            1774,
            1781,
            1784,
            1788,
            1780,
            1782,
            1788,
            1790,
            1796,
            1790,
            1798,
            1789,
            1792,
            1789,
            1797,
            1791,
            1795,
            1789,
            1797,
            1796,
            1808,
            1801,
            1799,
            1802,
            1800,
            1800,
            1798,
            1805,
            1799,
            1801,
            1812,
            1809,
            1810,
            1813,
            1813,
            1807,
            1810,
            1810,
            1805,
            1812,
            1810,
            1816,
            1819,
            1818,
            1824,
            1821,
            1817,
            1818,
            1820,
            1826,
            1825,
            1825,
            1825,
            1828,
            1830,
            1832,
            1841,
            1849,
            1860,
            1875,
            1879,
            1895,
            1914,
            1929,
            1941,
            1953,
            1963,
            1972,
            1973,
            1982,
            1993,
            2001,
            2007,
            2009,
            2009,
            2013,
            2012,
            2005,
            2000,
            1992,
            1981,
            1973,
            1957,
            1955,
            1939,
            1927,
            1922,
            1907,
            1895,
            1878,
            1871,
            1857,
            1846,
            1833,
            1825,
            1811,
            1807,
            1809,
            1808,
            1814,
            1813,
            1816,
            1815,
            1819,
            1821,
            1830,
            1824,
            1821,
            1819,
            1823,
            1824,
            1828,
            1825,
            1832,
            1832,
            1833,
            1824,
            1827,
            1827,
            1823,
            1832,
            1840,
            1838,
            1841,
            1837,
            1835,
            1837,
            1836,
            1831,
            1831,
            1830,
            1833,
            1832,
            1816,
            1796,
            1753,
            1706,
            1658,
            1611,
            1583,
            1591,
            1640,
            1715,
            1833,
            1988,
            2150,
            2334,
            2516,
            2699,
            2880,
            3027,
            3144,
            3183,
            3161,
            3083,
            2950,
            2785,
            2595,
            2383,
            2192,
            2013,
            1853,
            1740,
            1659,
            1614,
            1583,
            1569,
            1582,
            1598,
            1645,
            1685,
            1721,
            1759,
            1771,
            1781,
            1780,
            1780,
            1766,
            1768,
            1758,
            1765,
            1761,
            1766,
            1769,
            1771,
            1777,
            1775,
            1779,
            1775,
            1779,
            1784,
            1779,
            1784,
            1779,
            1788,
            1788,
            1782,
            1781,
            1785,
            1785,
            1789,
            1788,
            1791,
            1783,
            1788,
            1788,
            1792,
            1789,
            1788,
            1788,
            1788,
            1787,
            1791,
            1789,
            1791,
            1798,
            1792,
            1789,
            1796,
            1795,
            1791,
            1800,
            1796,
            1802,
            1802,
            1804,
            1802,
            1805,
            1804,
            1798,
            1803,
            1807,
            1813,
            1829,
            1848,
            1861,
            1871,
            1883,
            1900,
            1920,
            1936,
            1937,
            1953,
            1971,
            1981,
            1995,
            2005,
            2023,
            2031,
            2046,
            2055,
            2063,
            2080,
            2085,
            2095,
            2108,
            2121,
            2126,
            2137,
            2148,
            2150,
            2153,
            2162,
            2159,
            2159,
            2163,
            2165,
            2166,
            2164,
            2162,
            2162,
            2157,
            2154,
            2155,
            2143,
            2144,
            2137,
            2129,
            2122,
            2114,
            2104,
            2093,
            2088,
            2082,
            2064,
            2053,
            2046,
            2027,
            2020,
            2000,
            1984,
            1967,
            1962,
            1945,
            1922,
            1909,
            1894,
            1882,
            1869,
            1851,
            1838,
            1827,
            1816,
            1795,
            1788,
            1772,
            1760,
            1748
        )
    )

    val FREQ = 24 // frames-per-seconds
    val PERIOD = 1000 // 1s = 1000ms
    var _drawSeriesLength //  Drawable data size per second
            : Int
    var buffer_: CircularBuffer<Int?>
    private val utils_ = Utils()
    private var step = 0.0
    var path: Path? = null
    var pathBefore: Path? = null
    var pathAfter: Path? = null
    var point: Offset? = null
    private var ident_: String? = null
    var full = false
    var writeIndex = 0
    var readIndex = 0
    var size = 0

    init {
        _drawSeriesLength = (_seriesLength / (PERIOD.toDouble() / FREQ)).toInt() + 1
        buffer_ = CircularBuffer(_seriesLength * _seriesNumber)
    }

    fun buffer(): CircularBuffer<Int?> {
        return buffer_
    }

    fun drawingFrequency(): Int {
        return (rowData.size / _drawSeriesLength.toDouble()).toInt()
    }

    fun seriesLength(): Int {
        return _drawSeriesLength
    }

    fun setIdent(ident: String?) {
        ident_ = ident
    }

    fun updateBuffer(counter: Int) {
        val seriesSize = seriesLength()
        val dataExtracted = utils_.extractRangeData(rowData, (counter - 1) * seriesSize, seriesSize)
        buffer_.writeRow(dataExtracted)
        //Log.d(TAG,"[${buffer_.size()}]")
    }

    val min: Double
        get() {
            var minV = 0
            val rowData = buffer_.buffer()
            if (buffer_.size() === buffer_.capacity() - 1) {
                minV = utils_.getMinForFullBuffer(buffer_)
                for (i in 1 until buffer_.capacity()) {
                    val value = rowData[i]
                    if (value != null) {
                        if (value < minV) {
                            minV = value
                        }
                    }
                }
            } else {
                minV = rowData[0]!!
                for (i in 1 until buffer_.size()) {
                    if (i < rowData.size) {
                        if (rowData[i]!! < minV) {
                            minV = rowData[i]!!
                        }
                    } else {
                        Log.e(TAG, "Min error")
                    }
                }
            }
            return minV.toDouble()
        }
    val max: Double
        get() {
            var maxV = 0
            val rowData = buffer_.buffer()
            if (buffer_.size() === buffer_.capacity() - 1) {
                maxV = utils_.getMinForFullBuffer(buffer_)
                for (i in 1 until buffer_.capacity()) {
                    val value = rowData[i]
                    if (value != null) {
                        if (value > maxV) {
                            maxV = value
                        }
                    }
                }
            } else {
                maxV = rowData[0]!!
                for (i in 1 until buffer_.size()) {
                    if (i < rowData.size) {
                        if (rowData[i]!! > maxV) {
                            maxV = rowData[i]!!
                        }
                    } else {
                        Log.e(TAG, "Max error")
                    }
                }
            }
            return maxV.toDouble()
        }

    fun prepareData(size: Size, shiftH: Double): List<Double> {
        var data: MutableList<Double> = ArrayList()
        val width = size.width.toDouble()
        val height = size.height.toDouble()
        var minV = min
        var maxV = max
        if (minV == maxV) {
            minV = minV / 2
            maxV = maxV + minV / 2
        }
        val dv = maxV - minV
        step = width / buffer_.capacity()
        val coeff = (height - 2 * shiftH) / dv
        val dataTemp =
            if (_mode == GraphMode.Overlay) utils_.dataSeriesOverlay(buffer_) else utils_.dataSeriesNormal(
                this
            )
        data = ArrayList(Collections.nCopies(dataTemp.size, 0.0))
        if (data.isEmpty() || dataTemp.isEmpty()) {
            return data
        }
        for (i in dataTemp.indices) {
            data[i] = (maxV - dataTemp[i]!!) * coeff + shiftH
        }
        return data
    }

    fun preparePath(data: List<Double>): Path {
        val path = Path()
        path.moveTo(0f, data[0].toFloat())
        for (i in 1 until data.size) {
            path.lineTo((i * step).toFloat(), data[i].toFloat())
        }
        return path
    }

    fun preparePathBefore(data: List<Double>): Path {
        var idx_ = buffer_.writeIndex() - 1
        if (idx_ >= data.size) {
            idx_ = data.size - 1
            Log.e(TAG, "prepareBefore")
        }
        val idx = if (idx_ < 0) 0 else idx_
        val path = Path()
        path.moveTo(0f, data[0].toFloat())
        for (i in 1 until idx - 1) {
            path.lineTo((i * step).toFloat(), data[i].toFloat())
        }
        return path
    }

    fun preparePathAfter(data: List<Double>): Path {
        var idx_ = buffer_.writeIndex() // -1???
        if (idx_ >= data.size) {
            //Log.e(TAG, "[" + ident_ + "] preparePathAfter [" + idx_ + "->" +  data.size() + "]");
            idx_ = data.size - 1
        }
        val idx = if (idx_ < buffer_.capacity() - 1) idx_ else buffer_.capacity() - 1
        val path = Path()
        path.moveTo((idx * step).toFloat(), data[idx].toFloat())
        for (i in idx + 1 until data.size) {
            path.lineTo((i * step).toFloat(), data[i].toFloat())
        }
        return path
    }

    fun preparePoint(data: List<Double>): Offset {
        var idx_ = buffer_.writeIndex() - 1
        if (idx_ >= data.size) {
            idx_ = data.size - 1
            Log.e(TAG, "preparePoint")
        }
        val idx = if (idx_ < 0) 0 else idx_
        return Offset((idx * step).toFloat(), data[idx].toFloat())
    }

    fun prepareDrawing(size: Size, shiftH: Double) {
        val data = prepareData(size, shiftH)
        path = preparePath(data)
        pathBefore = preparePathBefore(data)
        pathAfter = preparePathAfter(data)
        point = preparePoint(data)
    }

    fun storeCircularBufferParams() {
        full = buffer_.isFull
        writeIndex = buffer_.writeIndex()
        readIndex = buffer_.readIndex()
        size = buffer_.size()
    }

    fun restoreCircularBufferParams() {
        buffer_.setFull(full)
        buffer_.setWriteIndex(writeIndex)
        buffer_.setReadIndex(readIndex)
        buffer_.setSize(size)
    }

    fun setMode(mode: GraphMode) {
        _mode = mode
    }

    fun mode(): GraphMode {
        return _mode
    }

    fun isFull(): Boolean {
        return buffer_.isFull
    }
}
